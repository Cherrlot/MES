package com.zhizhunbao.module.mine.viewmodel

import com.alibaba.android.arouter.launcher.ARouter
import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.net.initiateRequest
import com.zhizhunbao.lib.common.repository.SettingRepository
import com.zhizhunbao.lib.common.repository.UserRepository
import com.zhizhunbao.lib.common.router.ROUTER_PATH_LOGIN
import com.zhizhunbao.lib.common.util.AppManager
import com.zhizhunbao.lib.common.util.NetWorkUtil
import com.zhizhunbao.lib.common.util.SingleLiveEvent
import org.json.JSONObject
import org.koin.java.KoinJavaComponent

/**
 * 设置
 */
class SettingViewModel: BaseViewModel() {
    private val mRepository: UserRepository by KoinJavaComponent.inject(UserRepository::class.java)
    private val mMineRepository: SettingRepository by KoinJavaComponent.inject(SettingRepository::class.java)

    /** 返回点击 */
    val onBackClick: () -> Unit = {
        mFinishLiveData.value = true
    }

    /** 退出当前界面确认 */
    val mFinishLiveData = SingleLiveEvent<Boolean>()

    /**
     * 退出
     */
    fun logout() {
        gotoLogin()
        return
    }

    /**
     * 登录
     */
    private fun gotoLogin() {
        AppLocalData.resetAll()
        AppManager.finishAllActivity()
        ARouter.getInstance().build(ROUTER_PATH_LOGIN).navigation()
    }

    /**
     * 修改密码
     */
    fun editPwdPhone(oldPassword: String, newPassword: String) {
        showLoading()
        val jsonObject = JSONObject()
        jsonObject.put("old", oldPassword)
        jsonObject.put("password", newPassword)
        jsonObject.put("reinput", newPassword)
        initiateRequest({ mMineRepository.editPwd(NetWorkUtil.getRequestBody(jsonObject.toString())) },
            success = {
                "修改成功".toast()
                hideLoading()
                mFinishLiveData.value = true
            },
            failed = { s: String?, _: StateType ->
                hideLoading()
                s.toast()
            })
    }

}