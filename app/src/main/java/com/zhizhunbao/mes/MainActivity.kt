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
import com.zhizhunbao.lib.common.ext.safe
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
import com.zhizhunbao.lib.common.tool.getAssetsStreamByName
import com.zhizhunbao.lib.common.tool.string
import com.zhizhunbao.lib.common.util.AppManager
import com.zhizhunbao.lib.common.websocket.AppWebsocket
import com.zhizhunbao.mes.databinding.ActivityMainBinding
import java.io.UnsupportedEncodingException
import java.util.Vector

@Route(path = ROUTER_PATH_MAIN)
class MainActivity : BaseAppActivity<MainViewModel, ActivityMainBinding>() {
    private val Command: Vector<Byte> = Vector<Byte>()

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
            .addStatusChangeListener(object :
                BluetoothStatusChangeListener {
                override fun onConnect() {
                    addStrToCommand("SIZE 40 mm,30 mm\r\nGAP 2 mm,0 mm\r\n")
                    addStrToCommand("HOME\r\n")
                    PrintBluetoothManager.sendCommand(Command)
                    Command.clear()
                }
            })
            .startRead(resultListener = {}, onError = {
                viewModel.showLoading()
                mBinding.navView.postDelayed({
                    viewModel.hideLoading()
                    initBluetooth()
                }, 500)
                it.toast()
            })
    }

    private fun addStrToCommand(str: String) {
        var bs: ByteArray? = null
        if (str != "") {
            try {
                bs = str.toByteArray(charset("GB2312"))
            } catch (var4: UnsupportedEncodingException) {
                var4.printStackTrace()
            }
            for (i in bs!!.indices) {
                this.Command.add(bs[i])
            }
        }
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