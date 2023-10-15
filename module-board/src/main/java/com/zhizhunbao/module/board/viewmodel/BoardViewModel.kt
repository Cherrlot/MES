package com.zhizhunbao.module.board.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.bean.BoardBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.net.constant.State
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.net.initiateRequest
import com.zhizhunbao.lib.common.repository.UserRepository
import org.koin.java.KoinJavaComponent

/**
 * 看板列表
 */
class BoardViewModel : BaseViewModel() {
    private val mRepository: UserRepository by KoinJavaComponent.inject(UserRepository::class.java)

    /** 页码 */
    var mPage = 1

    /** 总页数 */
    var mTotalPage = 0

    /** 看板数据 */
    var mBoardList = MutableLiveData<MutableList<BoardBean>>()

    /**
     * 获取数据
     */
    fun getDataList() {
        val map = HashMap<String, Any?>()
        map["page"] = mPage

        initiateRequest(
            {
                mRepository.getBoardList(map)
            },
            success = {
                if (it.isNullOrEmpty()) {
                    mTotalPage = mPage
                }
                mBoardList.value = it

                // 显示空数据页或者隐藏错误页
                netRequestState.value =
                    if (it.isNullOrEmpty() && mPage == 1)
                        State(StateType.EMPTY)
                    else
                        State(StateType.SUCCESS)
            },
            failed = { s: String?, stateType: StateType ->
                // 加载完成
                mBoardList.value = mutableListOf()
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