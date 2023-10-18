package com.zhizhunbao.mes

import android.Manifest
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alibaba.android.arouter.launcher.ARouter
import com.jeremyliao.liveeventbus.LiveEventBus
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.constant.BUS_LOGIN
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.jpush.TagAliasOperatorHelper
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.permission.PermissionResult
import com.zhizhunbao.lib.common.permission.Permissions
import com.zhizhunbao.lib.common.router.ROUTER_PATH_LOGIN
import com.zhizhunbao.lib.common.router.ROUTER_PATH_MAIN
import com.zhizhunbao.lib.common.util.AppManager
import com.zhizhunbao.lib.common.viewmodel.SplashViewModel
import com.zhizhunbao.mes.databinding.ActivitySplashBinding
import com.zhizhunbao.module.login.LoginActivity
import listener.OnBtnClickListener
import model.UpdateConfig
import pub.devrel.easypermissions.AppSettingsDialog
import update.UpdateAppUtils

class SplashActivity : BaseAppActivity<SplashViewModel, ActivitySplashBinding>() {
    /**
     * 权限列表
     */
    private val mPermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private var mTime = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen()
        setContentView(R.layout.activity_splash)

        TagAliasOperatorHelper.getInstance().getRegistrationID(this)

        mTime = System.currentTimeMillis()
        initPermission()
    }

    override fun initObserve() {
        LiveEventBus.get(BUS_LOGIN, Boolean::class.java).observe(this) {
            startLogin()
        }

        viewModel.run {
            isUpdateOk.observe(this@SplashActivity) {
                if (it && isInitOk.value.safe()) {
                    checkTime()
                }
            }
            isInitOk.observe(this@SplashActivity) {
                if (it && isUpdateOk.value.safe()) {
                    checkTime()
                }
            }
            mUpdateData.observe(this@SplashActivity) {
                if (it == null) {
                    isUpdateOk.value = true
                    return@observe
                }

                if (BuildConfig.VERSION_NAME < it.Val.safe("1.0.0")) {
                    val config = UpdateConfig().apply {
                        force = it.getForcedUpgrade()
                        checkWifi = true
                        alwaysShowDownLoadDialog = true
                    }
                    // 需要更新
                    UpdateAppUtils
                        .getInstance()
                        .apkUrl(it.Memo.safe())
                        .updateTitle("版本更新")
                        .updateContent(it.description.safe())
                        .updateConfig(config)
                        .setCancelBtnClickListener(object : OnBtnClickListener {
                            override fun onClick(): Boolean {
                                isUpdateOk.postValue(true)
                                return false
                            }
                        })
                        .update()
                } else {
                    isUpdateOk.value = true
                }
            }
        }
    }

    private fun initPermission() {
        Permissions(this).request(*mPermissions).observe(
            this
        ) {
            when (it) {
                is PermissionResult.Grant -> {
                    viewModel.initLogSave(this)
                    viewModel.checkUpdate()
                }
                // 进入设置界面申请权限
                is PermissionResult.Rationale -> {
                    AppSettingsDialog.Builder(this)
                        .setTitle("申请权限")
                        .setRationale("没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改")
                        .build()
                        .show()
                    finish()
                }
                // 进入设置界面申请权限
                is PermissionResult.Deny -> {
                    AppSettingsDialog.Builder(this)
                        .setTitle("申请权限")
                        .setRationale("没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改")
                        .build()
                        .show()
                    finish()
                }
            }
        }
    }

    /**
     * 两秒后进入主界面
     */
    private fun checkTime() {
//        mBinding.root.viewTreeObserver.addOnPreDrawListener {
//            // 闪屏页停留
//            splashFinish()
//            true
//        }
        val lastTime = 2000 - (System.currentTimeMillis() - mTime)
        if (lastTime <= 0) {
            splashFinish()
        } else {
            mBinding.root.postDelayed({
                splashFinish()
            }, lastTime)
        }
    }

    /**
     * 加载完成
     */
    private fun splashFinish() {
        // 是否是首次启动
        when {
            AppLocalData.isFirstStart -> {
                // 去引导页
                startLogin()
            }
            AppLocalData.token.isBlank() -> {
                // 去登陆页
                startLogin()
            }
            else -> {
                // 首页
                ARouter.getInstance().build(ROUTER_PATH_MAIN).navigation()
            }
        }
        AppLocalData.isFirstStart = false
        finish()
    }

    private fun startLogin() {
        if (!AppManager.contains(LoginActivity::class.java)) {
            ARouter.getInstance().build(ROUTER_PATH_LOGIN).navigation()
        }
    }
}