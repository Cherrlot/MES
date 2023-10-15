package com.zhizhunbao.module.mine.activity

import android.os.Bundle
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.dialog.CustomNormalDialog
import com.zhizhunbao.lib.common.ext.startTargetActivity
import com.zhizhunbao.module.mine.R
import com.zhizhunbao.module.mine.databinding.ActivitySettingBinding
import com.zhizhunbao.module.mine.listener.SettingListener
import com.zhizhunbao.module.mine.viewmodel.SettingViewModel

/**
 * 系统设置
 */
class SettingActivity: BaseAppActivity<SettingViewModel, ActivitySettingBinding>(),
    SettingListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mBinding.listener = this
    }

    override fun initObserve() {
        viewModel.mFinishLiveData.observe(this) {
            if (it)
                finish()
        }
    }

    /**
     * 退出登录
     */
    override fun onQuitClick() {
        CustomNormalDialog(this).show{
            setMessage(com.zhizhunbao.lib.common.R.string.confirm_logout)
            setPositiveButton {
                viewModel.logout()
            }
        }
    }

    /**
     * 修改密码
     */
    override fun onPwdClick() {
        startTargetActivity<ResetPwActivity>()
    }
}