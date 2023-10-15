package com.zhizhunbao.mes

import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.net.initiateRequest
import com.zhizhunbao.lib.common.repository.UserRepository
import com.zhizhunbao.lib.common.util.NetWorkUtil
import org.json.JSONObject
import org.koin.java.KoinJavaComponent

class MainViewModel:BaseViewModel() {
    private val mRepository: UserRepository by KoinJavaComponent.inject(UserRepository::class.java)

    fun getQrDetail(qrCode:String) {
        val map = HashMap<String, Any?>()
        map["no"] = qrCode
//        map["materials"] = "{\"no\":\"$qrCode\"}"

        initiateRequest({ mRepository.scanQrcode(map) },
            success = {
            },
            failed = { s: String?, _: StateType ->
                s.toast()
                AppLog.e(s)
            })
    }

    /**
     * 极光推送绑定
     */
    fun addJPush() {
        val jsonObject = JSONObject()
        jsonObject.put("Id", AppLocalData.authId)
        jsonObject.put("Jpushid", AppLocalData.jPushId)

        initiateRequest({ mRepository.addJPush(NetWorkUtil.getRequestBody(jsonObject.toString())) },
            success = {
            },
            failed = { s: String?, _: StateType ->
            })
    }

}