package com.zhizhunbao.module.mine.activity

import android.os.Bundle
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.ext.setOnThrottleClickListener
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.tool.string
import com.zhizhunbao.module.mine.R
import com.zhizhunbao.module.mine.databinding.ActivityResetPwBinding
import com.zhizhunbao.module.mine.viewmodel.SettingViewModel

/**
 * 修改密码
 */
class ResetPwActivity: BaseAppActivity<SettingViewModel, ActivityResetPwBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pw)

        mBinding.btnSave.setOnThrottleClickListener({
            if (mBinding.etPasswordOld.text.isNullOrBlank()) {
                com.zhizhunbao.lib.common.R.string.app_please_enter_old_password.string.toast()
                return@setOnThrottleClickListener
            }
            if (mBinding.etPasswordNew.text.isNullOrBlank()) {
                com.zhizhunbao.lib.common.R.string.app_please_enter_new_password.string.toast()
                return@setOnThrottleClickListener
            }
            if (mBinding.etPasswordConfirm.text.isNullOrBlank()) {
                com.zhizhunbao.lib.common.R.string.app_please_enter_password_again.string.toast()
                return@setOnThrottleClickListener
            }
            if (mBinding.etPasswordNew.text.toString() != mBinding.etPasswordConfirm.text.toString()) {
                com.zhizhunbao.lib.common.R.string.app_re_set_password_not_match.string.toast()
                return@setOnThrottleClickListener
            }
            viewModel.editPwdPhone(mBinding.etPasswordOld.text.toString(), mBinding.etPasswordNew.text.toString())
        })
    }

    override fun initObserve() {
        viewModel.mFinishLiveData.observe(this) {
            if (it)
                finish()
        }
    }
}