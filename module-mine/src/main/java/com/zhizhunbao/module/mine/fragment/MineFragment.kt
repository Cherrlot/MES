package com.zhizhunbao.module.mine.fragment

import com.zhizhunbao.lib.common.base.BaseAppFragment
import com.zhizhunbao.lib.common.ext.startTargetActivity
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.module.mine.R
import com.zhizhunbao.module.mine.activity.DeviceListActivity
import com.zhizhunbao.module.mine.activity.SettingActivity
import com.zhizhunbao.module.mine.databinding.FragmentMineBinding
import com.zhizhunbao.module.mine.listener.MineClickListener
import com.zhizhunbao.module.mine.viewmodel.MineViewModel

/**
 * 我的
 */
class MineFragment: BaseAppFragment<MineViewModel, FragmentMineBinding>(), MineClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_mine

    override fun initView() {
        mBinding.listener = this
        viewModel.mCompanyName.value = AppLocalData.companyInfo.Co
        viewModel.mUserName.value = AppLocalData.companyInfo.Descr
        viewModel.mAccountName.value = AppLocalData.companyInfo.Name
    }

    /**
     * 设备选择
     */
    override fun deviceClick() {
        requireContext().startTargetActivity<DeviceListActivity>()
    }

    /**
     * 设置
     */
    override fun settingClick() {
        requireContext().startTargetActivity<SettingActivity>()
    }
}