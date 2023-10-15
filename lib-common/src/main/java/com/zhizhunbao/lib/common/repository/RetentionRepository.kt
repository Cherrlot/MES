package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.local.LocalRetentionWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 菜单留样Repository
 */
class RetentionRepository(private val localRetentionWebService: LocalRetentionWebService)  {

    /**
     * 菜单留样
     */
    suspend fun retention(request: RequestBody) = withContext(Dispatchers.IO) {
        localRetentionWebService.retention(request)
    }

    /**
     * 查询未留样的记录
     */
    suspend fun getUnRetentionList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        localRetentionWebService.getUnRetentionList(map)
    }

}