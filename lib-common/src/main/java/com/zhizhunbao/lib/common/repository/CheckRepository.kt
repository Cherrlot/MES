package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.online.OnlineCheckWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 各种证件接口Repository
 */
class CheckRepository(
    private val mOnlineCheckWebService: OnlineCheckWebService
) {

    /**
     * 自检日期查询
     */
    suspend fun getSelfCheckDate(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        mOnlineCheckWebService.getSelfCheckDate(map)
    }

    /**
     * 自检信息新增
     */
    suspend fun createSelfCheckDate(request: RequestBody) = withContext(Dispatchers.IO) {
        mOnlineCheckWebService.createSelfCheckDate(request)
    }
    /**
     * 自检信息查询
     */
    suspend fun getSelfCheckInfo(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        mOnlineCheckWebService.getSelfCheckInfo(map)
    }

}