package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.local.LocalRetentionRecordWebService
import com.zhizhunbao.lib.common.net.service.online.OnlineRetentionRecordWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 菜单留样记录Repository
 */
class RetentionRecordRepository(
    private val localRetentionRecordWebService: LocalRetentionRecordWebService,
    private val onlineRetentionRecordWebService: OnlineRetentionRecordWebService
) {

    /**
     * 菜单留样记录列表
     */
    suspend fun retentionList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineRetentionRecordWebService.retentionList(map)
    }

    /**
     * 菜单留样记录详情
     */
    suspend fun retentionDetail(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineRetentionRecordWebService.retentionDetail(map)
    }

    /**
     * 菜单留样记录详情删除
     */
    suspend fun retentionDetailDelete(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineRetentionRecordWebService.retentionDetailDelete(request)
    }

}