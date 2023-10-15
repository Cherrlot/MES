package com.zhizhunbao.module.login

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.jpush.TagAliasOperatorHelper
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.router.ROUTER_PATH_LOGIN
import com.zhizhunbao.lib.common.router.ROUTER_PATH_MAIN
import com.zhizhunbao.lib.common.tool.string
import com.zhizhunbao.module.login.databinding.ActivityLoginBinding
import com.zhizhunbao.module.login.listener.LoginClickListener


/**
 * 登录界面
 */
@Route(path = ROUTER_PATH_LOGIN)
class LoginActivity : BaseAppActivity<LoginViewModel, ActivityLoginBinding>(), LoginClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = com.zhizhunbao.lib.common.R.string.app_login.string

        TagAliasOperatorHelper.getInstance().getRegistrationID(this)
        // 设置点击事件
        mBinding.listener = this
        viewModel.mUserName.value = AppLocalData.userName
        mBinding.etPw.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onLoginClick()
            }
            true
        }
    }

    override fun initObserve() {
        viewModel.mFinishLiveData.observe(this) {
            finish()
        }
        viewModel.mIsLogin.observe(this) {
            if (it) {
                // 去主界面
                ARouter.getInstance().build(ROUTER_PATH_MAIN).navigation()
                finish()
            }
        }
    }

    override fun onLoginClick() {
        viewModel.login()
    }

    override fun onRegisterClick(isRegister: Boolean) {
        viewModel.mRegister.value = isRegister
    }
}