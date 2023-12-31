package com.zhizhunbao.module.board.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.bean.OptionBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.State
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.net.initiateRequest
import com.zhizhunbao.lib.common.repository.UserRepository
import com.zhizhunbao.lib.common.util.NetWorkUtil
import com.zhizhunbao.lib.common.util.SingleLiveEvent
import org.json.JSONObject
import org.koin.java.KoinJavaComponent
import java.net.URLEncoder

/**
 * 工单列表
 */
class WorkOrderViewModel : BaseViewModel() {
    private val mRepository: UserRepository by KoinJavaComponent.inject(UserRepository::class.java)

    /** 返回点击 */
    val onBackClick: () -> Unit = {
        mFinishLiveData.value = true
    }

    /** 退出当前界面确认 */
    val mFinishLiveData = SingleLiveEvent<Boolean>()

    /** 页码 */
    var mPage = 1

    /** 总页数 */
    var mTotalPage = 0

    /** 操作列表 */
    var mOptionList = MutableLiveData<MutableList<OptionBean>>()

    /** 选中的操作表单*/
    var mOptionBean: OptionBean? = null
    fun replaceChineseCharacters(string: String?) : String? {
        //安卓6以上自动转换，无需再用此函数
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) return string
        else return string?.replace(Regex("(?<=/)[\\w\\s\\d\\u4e00-\\u9fa5.-]+(?=/?)")) { match ->
            return@replace URLEncoder.encode(match.value, "UTF-8")
        }
    }
    /**
     * 获取数据
     */
    fun getDataList() {
        val map = HashMap<String, Any?>()
//        map["orderby"] = "Starttime"
        map["orderby"] = "id"
//        map["Status"] = "Doing"
//        map["Status"] = "Released"
        map["Workplace"] = AppLocalData.workplace
        map["Machine"] = AppLocalData.machineNo
        map["page"] = mPage
        map["Status <>"] = "Closed"

        initiateRequest(
            {
                mRepository.getWorkOrderList(map)
            },
            success = {
                if (it.isNullOrEmpty()) {
                    mTotalPage = mPage
                }
                mOptionList.value = it

                // 显示空数据页或者隐藏错误页
                netRequestState.value =
                    if (it.isNullOrEmpty() && mPage == 1)
                        State(StateType.EMPTY)
                    else
                        State(StateType.SUCCESS)
            },
            failed = { s: String?, stateType: StateType ->
                // 加载完成
                mOptionList.value = mutableListOf()
                if (mPage == 1) {
                    // 第一次请求数据失败时显示错误提示
                    netRequestState.value = State(stateType, s.safe())
                } else {
                    mPage--
                    s.toast()
                }
            })
    }
}