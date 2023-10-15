package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.online.OnlineUserWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 用户Repository
 */
class UserRepository(
    private val onlineUserWebService: OnlineUserWebService,
) {
    /**
     * 登录
     */
    suspend fun login(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineUserWebService.login(request)
    }

    /**
     * 极光推送
     */
    suspend fun addJPush(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineUserWebService.addJPush(request)
    }

    /**
     * 退出登录
     */
    suspend fun logout(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineUserWebService.logout(request)
    }

    /**
     * 版本检查更新
     */
    suspend fun checkAppUpdate(type: String) = withContext(Dispatchers.IO) {
        onlineUserWebService.checkAppUpdate(type)
    }

    /**
     * 获取看板数据
     */
    suspend fun getBoardList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineUserWebService.getBoardList(map)
    }

    /**
     * 获取操作数据
     */
    suspend fun getOptionList(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineUserWebService.getOptionList(request)
    }

    /**
     * 获取工单列表
     */
    suspend fun getWorkOrderList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineUserWebService.getWorkOrderList(map)
    }

    /**
     * 扫码
     */
    suspend fun scanQrcode(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineUserWebService.scanQrcode(map)
    }
}