package com.zhizhunbao.mes

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeremyliao.liveeventbus.LiveEventBus
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.constant.BUS_LOGIN
import com.zhizhunbao.lib.common.constant.BUS_RECONNECT_PRINTER
import com.zhizhunbao.lib.common.dialog.SingleChoiceDialog
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.jpush.PushUtil
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.manager.BluetoothManager
import com.zhizhunbao.lib.common.manager.BluetoothStatusChangeListener
import com.zhizhunbao.lib.common.manager.PrintBluetoothManager
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.router.ROUTER_PATH_LOGIN
import com.zhizhunbao.lib.common.router.ROUTER_PATH_MAIN
import com.zhizhunbao.lib.common.tool.drawable
import com.zhizhunbao.lib.common.tool.string
import com.zhizhunbao.lib.common.util.AppManager
import com.zhizhunbao.lib.common.websocket.AppWebsocket
import com.zhizhunbao.mes.databinding.ActivityMainBinding
import com.zhizhunbao.module.gpsdk.DeviceConnFactoryManager
import com.zhizhunbao.module.gpsdk.PrintManager
import com.zhizhunbao.module.gpsdk.ThreadPool
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.Vector

@Route(path = ROUTER_PATH_MAIN)
class MainActivity : BaseAppActivity<MainViewModel, ActivityMainBinding>() {

    /** 上次返回点击时间 */
    private var mExitTime = 0L

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            "取消扫码".toast()
        } else {
            // 扫码
            viewModel.getQrDetail(result.contents)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.addJPush()

        val navView: BottomNavigationView = mBinding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        initBluetooth()

        AppLog.d("AppLocalData.machineNo:${AppLocalData.machineNo}")
        if (AppLocalData.machineNo.isNotEmpty()) {
            // 开启websocket
            AppWebsocket.appWebsocketConnect()
        }
    }

    /**
     * 连接蓝牙打印机
     */
//    private fun initBluetooth() {
//        BluetoothManager.builder(SingleChoiceDialog(this).apply {
//            setTitle("请选择蓝牙打印机")
//            setPositiveButton(listener = {
//                val macAddress = BluetoothManager.getDevicesInfo(it).address
//                /* 初始化话DeviceConnFactoryManager */
//                DeviceConnFactoryManager.Build()
//                .setId(0)
//                /* 设置连接方式 */
//                .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.BLUETOOTH)
//                /* 设置连接的蓝牙mac地址 */
//                .setMacAddress(macAddress)
//                .build()
//                /* 打开端口 */
//                val threadPool = ThreadPool.getInstantiation()
//                threadPool.addTask { DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].openPort() }
//                mBinding.navView.postDelayed({
//                    PrintManager.selfTest()
////                    val sb = StringBuilder()
////                    addStrToCommand("SIZE 40 mm,30 mm\r\n")
////                    addStrToCommand("DIRECTION 1,0\r\n")
////                    addStrToCommand("REFERENCE 0,0\r\n")
////                    addStrToCommand("CLS\r\n")
////                    addStrToCommand("TEXT 10,0,\"TSS24.BF2\",0,1,1,\"Welcome to use SMARNET printer\"\r\n")
////                    addStrToCommand("PRINT 1,1\r\n")
////                    addStrToCommand("SOUND 2,100\r\n")
////                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].sendDataImmediately(Command)
//                }, 500)
//            })
//            setNegativeButton(listener = {
//                BluetoothManager.cancelDiscovery()
//            })
//            setCanceledOnTouchOutside(false)
//        })
//    }

    private fun initBluetooth() {
        PrintBluetoothManager.builder(SingleChoiceDialog(this).apply {
            setTitle("请选择蓝牙打印机")
            setPositiveButton(listener = {
                PrintBluetoothManager.connectDevice(it)
            })
            setNegativeButton(listener = {
                PrintBluetoothManager.cancelDiscovery()
            })
            setCanceledOnTouchOutside(false)
        })
//            .addStatusChangeListener(object :
//            BluetoothStatusChangeListener {
//            override fun onConnect() {
//                // 修改为cpcl模式
////                for (i in PrintBluetoothManager.mCpclMode.indices) {
////                    Command.add(PrintBluetoothManager.mCpclMode[i])
////                }
////                PrintBluetoothManager.sendCommand(Command)
////                Command.clear()
////                addStrToCommand("SOUND 2,100\r\n")
////                PrintBluetoothManager.sendCommand(Command)
////                PrintBluetoothManager.changeMode(PrintBluetoothManager.mCpclMode)
//                addStrToCommand("SIZE 40 mm,30 mm\r\nDIRECTION 1,0\r\nREFERENCE 0,0\r\nCLS\r\nTEXT 10,0,\"TSS24.BF2\",0,1,1,\"Welcome to use SMARNET printer\"\r\nPRINT 1,1\r\nSOUND 2,100\r\n")
//                PrintBluetoothManager.sendCommand(Command)
//            }
//        })
            .startRead(resultListener = {}, onError = {
            viewModel.showLoading()
            mBinding.navView.postDelayed({
                viewModel.hideLoading()
                initBluetooth()
            }, 500)
            it.toast()
        })
    }

    private fun resetOptionItem(optionItem: MenuItem?) {
        optionItem?.title = com.zhizhunbao.lib.common.R.string.option.string
        optionItem?.icon = com.zhizhunbao.lib.common.R.drawable.ic_menu_setting.drawable
    }

    override fun onResume() {
        super.onResume()
        PushUtil.setNum(0)
    }

    override fun initObserve() {
        LiveEventBus.get(BUS_RECONNECT_PRINTER, Boolean::class.java).observe(this) {
            // 重新连接打印机
            initBluetooth()
        }
        // 未登录
        LiveEventBus.get(BUS_LOGIN, Boolean::class.java).observe(this) {
            "请重新登录".toast()
            AppLocalData.token = ""
            AppLocalData.authId = ""
            AppManager.finishAllActivity()
            ARouter.getInstance().build(ROUTER_PATH_LOGIN).navigation()
        }
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (System.currentTimeMillis() - mExitTime > 2000) {
                        "再按一次退出程序".toast()
                        mExitTime = System.currentTimeMillis()
                    } else {
                        AppManager.appExit()
                    }
                }
            })
    }

    override fun onStop() {
        BluetoothManager.cancelDiscovery()
        PrintBluetoothManager.cancelDiscovery()
        super.onStop()
    }

    override fun onDestroy() {
        PrintBluetoothManager.onDestroy()
        BluetoothManager.onDestroy()
        super.onDestroy()
    }

}