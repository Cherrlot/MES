package com.zhizhunbao.module.board.viewmodel

import com.jeremyliao.liveeventbus.LiveEventBus
import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.bean.OptionBean
import com.zhizhunbao.lib.common.bean.OptionListBean
import com.zhizhunbao.lib.common.constant.BUS_REFRESH_OPTION
import com.zhizhunbao.lib.common.constant.BUS_REFRESH_WORK_ORDER
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.net.initiateRequest
import com.zhizhunbao.lib.common.repository.UserRepository
import com.zhizhunbao.lib.common.util.NetWorkUtil
import com.zhizhunbao.lib.common.util.SingleLiveEvent
import org.json.JSONArray
import org.json.JSONObject
import org.koin.java.KoinJavaComponent

/**
 * 操作列表
 */
class OptionViewModel : BaseViewModel() {
    private val mRepository: UserRepository by KoinJavaComponent.inject(UserRepository::class.java)

    /** 返回点击 */
    val onBackClick: () -> Unit = {
        mFinishLiveData.value = true
    }

    /** 退出当前界面确认 */
    val mFinishLiveData = SingleLiveEvent<Boolean>()

    /** 提交结果 */
    val mResultLiveData = SingleLiveEvent<Boolean>()

    /** 选中的操作表单*/
    var mOptionListBean: SingleLiveEvent<OptionListBean?> = SingleLiveEvent()

    /** 选中的操作表单*/
    var mOptionBean: SingleLiveEvent<OptionBean?> = SingleLiveEvent()

    /**
     * 扫码提交
     */
    fun scanAndSubmit(code: String) {
        val jsonObject = JSONObject()
        jsonObject.put("Id", mOptionBean.value?.Id)
        jsonObject.put("Status", mOptionListBean.value?.No)
        if (code.isNotBlank())
            jsonObject.put("code", code)
        jsonObject.put("machine", AppLocalData.machineNo)
        jsonObject.put("workplace", AppLocalData.workplace)
        mOptionListBean.value?.Items?.let { options ->
            options.forEach { optionItemBean ->
                if (!optionItemBean.groupValue.isNullOrEmpty()) {
                    jsonObject.put("group", optionItemBean.groupValue)
                }
                when (optionItemBean.type) {
                    "input" -> {
                        var value = optionItemBean.inputValue.safe()
                        if(optionItemBean.format == "number" && value.trim().isEmpty()) {
                            value = "0"
                        }
                        jsonObject.put(optionItemBean.field.safe(), value)
                    }
                    "radio" -> jsonObject.put(optionItemBean.field.safe(), optionItemBean.radioValue.safe())
                    "checkbox" -> {
                        val jsonArray = JSONArray()
                        optionItemBean.checkValue?.forEach { value ->
                            jsonArray.put(value)
                        }
                        jsonObject.put(
                            optionItemBean.field.safe(),
                            jsonArray
                        )
                    }
                }
            }
        }

        showLoading()
        initiateRequest(
            {
                mRepository.getOptionList(NetWorkUtil.getRequestBody(jsonObject.toString()))
            },
            success = {
                mOptionBean.value = it?.get(0)
                LiveEventBus.get(BUS_REFRESH_WORK_ORDER).post(true)
                LiveEventBus.get(BUS_REFRESH_OPTION).post(true)
                hideLoading()
                "提交成功".toast()
                mResultLiveData.value = true
//                mFinishLiveData.value = true
            },
            failed = { s: String?, _: StateType ->
                hideLoading()
                s.toast()
                mResultLiveData.value = false
//                mFinishLiveData.value = false
            })
    }

    /**
     * 输入提交
     */
    fun submit() {
        scanAndSubmit("")
    }
}