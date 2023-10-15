package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.local.LocalReserveWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 菜单预定Repository
 */
class ReserveRepository(private val localReserveWebService: LocalReserveWebService)  {
    /**
     * 获取菜单预定列表
     */
    suspend fun getReserveList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        localReserveWebService.getReserveList(map)
    }

    /**
     * 获取菜单预定详情
     */
    suspend fun getReserveDetail(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        localReserveWebService.getReserveDetail(map)
    }

    /**
     * 菜单预定创建
     */
    suspend fun reserveCreate(request: RequestBody) = withContext(Dispatchers.IO) {
        localReserveWebService.reserveCreate(request)
    }

    /**
     * 菜单预定编辑
     */
    suspend fun reserveUpdate(request: RequestBody) = withContext(Dispatchers.IO) {
        localReserveWebService.reserveUpdate(request)
    }

}