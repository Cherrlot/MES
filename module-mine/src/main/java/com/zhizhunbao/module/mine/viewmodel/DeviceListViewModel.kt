package com.zhizhunbao.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.bean.MachineBean
import com.zhizhunbao.lib.common.bean.OptionListBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.State
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
class DeviceListViewModel: BaseViewModel() {
    private val mRepository: SettingRepository by KoinJavaComponent.inject(SettingRepository::class.java)

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
    var mMachineList = MutableLiveData<MutableList<MachineBean>>()

    /**
     * 获取数据
     */
    fun getDataList() {
        val map = HashMap<String, Any?>()
        map["orderby"] = "no"

        initiateRequest(
            {
                mRepository.getDeviceList(map)
            },
            success = {
                if (it.isNullOrEmpty()) {
                    mTotalPage = mPage
                }

                mMachineList.value = it
                // 显示空数据页或者隐藏错误页
                netRequestState.value =
                    if (it.isNullOrEmpty() && mPage == 1)
                        State(StateType.EMPTY)
                    else
                        State(StateType.SUCCESS)
            },
            failed = { s: String?, stateType: StateType ->
                // 加载完成
                mMachineList.value = mutableListOf()
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