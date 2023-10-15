package com.zhizhunbao.mes

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.core.view.forEach
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeremyliao.liveeventbus.LiveEventBus
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.constant.BUS_LOGIN
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.jpush.PushUtil
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.qrcode.ToolbarCaptureActivity
import com.zhizhunbao.lib.common.router.ROUTER_PATH_LOGIN
import com.zhizhunbao.lib.common.router.ROUTER_PATH_MAIN
import com.zhizhunbao.lib.common.tool.drawable
import com.zhizhunbao.lib.common.tool.string
import com.zhizhunbao.lib.common.util.AppManager
import com.zhizhunbao.lib.common.websocket.AppWebsocket
import com.zhizhunbao.mes.databinding.ActivityMainBinding

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

        if (AppLocalData.machineNo.isNotEmpty()) {
            // 开启websocket
            AppWebsocket.appWebsocketConnect()
        }

//        var optionItem: MenuItem? = null
//        navView.menu.forEach { item ->
//            if (item.title == com.zhizhunbao.lib.common.R.string.option.string)
//                optionItem = item
//        }
//
//        navView.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_board -> {
//                    resetOptionItem(optionItem)
//                    navController.navigate(R.id.navigation_board)
//                }
//                R.id.navigation_mine -> {
//                    resetOptionItem(optionItem)
//                    navController.navigate(R.id.navigation_mine)
//                }
//                R.id.navigation_scan -> {
//                    if (item.title == com.zhizhunbao.lib.common.R.string.option.string) {
//                        // 进入操作页面
//                        navController.navigate(R.id.navigation_scan)
//                        item.title = com.zhizhunbao.lib.common.R.string.scan.string
//                        item.icon = com.zhizhunbao.lib.common.R.drawable.icon_qr_code.drawable
//                    } else {
//                        // 扫码提交
//                        val options = ScanOptions()
//                        options.captureActivity = ToolbarCaptureActivity::class.java
//                        barcodeLauncher.launch(options)
//
//                        resetOptionItem(optionItem)
//                        return@setOnItemSelectedListener false
//                    }
//                }
//            }
//            true
//        }
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

}