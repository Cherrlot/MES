package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.service.local.LocalLimitWebService
import com.zhizhunbao.lib.common.net.service.online.OnlineLimitWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 消费限额Repository
 */
class LimitRepository(
    private val localLimitWebService: LocalLimitWebService,
    private val onlineLimitWebService: OnlineLimitWebService
) {
    /**
     * 消费限额列表查询
     */
    suspend fun getLimitList() = withContext(Dispatchers.IO) {
        onlineLimitWebService.getLimitList(AppLocalData.diningInfo?.id.safe())
    }

    /**
     * 消费限额创建
     */
    suspend fun limitCreate(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineLimitWebService.limitCreate(request)
    }

    /**
     * 消费限额删除
     */
    suspend fun limitDelete(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineLimitWebService.limitDelete(request)
    }

    /**
     * 消费限额编辑
     */
    suspend fun limitModify(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineLimitWebService.limitModify(request)
    }
}