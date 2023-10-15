package com.zhizhunbao.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhizhunbao.lib.common.base.BaseViewModel

/**
 * 我的
 */
class MineViewModel: BaseViewModel() {

    /** 监管公司 */
    val mCompanyName: MutableLiveData<String> = MutableLiveData("")

    /** 用户名 */
    val mUserName: MutableLiveData<String> = MutableLiveData("")

    /** 账号 */
    val mAccountName: MutableLiveData<String> = MutableLiveData("")

}