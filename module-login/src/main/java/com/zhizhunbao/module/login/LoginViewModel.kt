package com.zhizhunbao.module.login

import androidx.lifecycle.MutableLiveData
import com.jeremyliao.liveeventbus.LiveEventBus
import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.bean.CompanyInfoBean
import com.zhizhunbao.lib.common.constant.BUS_CHANGE_DINING
import com.zhizhunbao.lib.common.constant.PASSWORD_MIN_LENGTH
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.net.initiateRequest
import com.zhizhunbao.lib.common.repository.DishRepository
import com.zhizhunbao.lib.common.repository.UserRepository
import com.zhizhunbao.lib.common.tool.string
import com.zhizhunbao.lib.common.util.NetWorkUtil.getRequestBody
import com.zhizhunbao.lib.common.util.SingleLiveEvent
import org.json.JSONArray
import org.json.JSONObject
import org.koin.java.KoinJavaComponent.inject


class LoginViewModel : BaseViewModel() {
    private val mRepository: UserRepository by inject(UserRepository::class.java)
    private val mDishRepository: DishRepository by inject(DishRepository::class.java)

    /** 返回点击 */
    val onBackClick: () -> Unit = {
        mFinishLiveData.value = true
    }

    /** 退出当前界面确认 */
    val mFinishLiveData = SingleLiveEvent<Boolean>()
    /**
     * 登录成功
     */
    var mIsLogin = MutableLiveData<Boolean>().apply { this.value = false }

    /** 标记 - 是否是注册 */
    var mRegister: MutableLiveData<Boolean> = MutableLiveData(true)

    val mUserName: MutableLiveData<String> = MutableLiveData("")

    val mPassword: MutableLiveData<String> = MutableLiveData("")

    /**
     * 登录
     */
    fun login() {
        val name = mUserName.value
        val password = mPassword.value

        if (name.isNullOrBlank()) {
            // 用户名为空
            (com.zhizhunbao.lib.common.R.string.app_please_enter_user_name.string).toast()
            return
        }
        if (password.isNullOrBlank()) {
            // 密码为空
            (com.zhizhunbao.lib.common.R.string.app_password_must_not_be_empty.string).toast()
            return
        }
        if (password.orEmpty().length < PASSWORD_MIN_LENGTH) {
            // 密码长度小于最低长度
            (com.zhizhunbao.lib.common.R.string.app_password_length_must_larger_than_six.string).toast()
            return
        }

        AppLocalData.userName = name.safe()

        showLoading(com.zhizhunbao.lib.common.R.string.login_loading.string)
        val params = JSONObject()
        params.put("account", name)
        params.put("pwd", password)
        initiateRequest({ mRepository.login(getRequestBody(params.toString())) },
            success = {
                AppLocalData.token = it.token.safe()
                AppLocalData.authId = it.Id.safe()
                AppLocalData.userId = it.userId.safe()
                AppLocalData.companyId = it.companyId.safe()
                AppLocalData.companyInfo = CompanyInfoBean().apply {
                    Name = it.Name
                    Descr = it.Descr
                    Co = it.Co
                }

                hideLoading()
                // 登录成功
                mIsLogin.value = true
            },
            failed = { s: String?, _: StateType ->
                failed(s)
            })
    }

    /**
     * 获取用户信息
     */
    private fun getAccountInfo() {
        initiateRequest({ mDishRepository.getAccountInfo() },
            success = {
                AppLocalData.accountInfo = it
                // 登录成功
                mIsLogin.value = true
                hideLoading()
            },
            failed = { s: String?, _: StateType ->
                failed(s)
            })
    }

    private fun failed(s: String?) {
        AppLocalData.token = ""
        AppLocalData.authId = ""
        hideLoading()
        s?.let {
            s.toast()
            AppLog.e(s)
        }
    }
}