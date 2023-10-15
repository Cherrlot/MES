package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.online.OnlineNotifyWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 自查通知Repository
 */
class SelfNotifyRepository(
    private val onlineNotifyWebService: OnlineNotifyWebService
) {
    /**
     * 消息列表
     */
    suspend fun getMessageList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineNotifyWebService.getMessageList(map)
    }

    /**
     * 自查通知数量
     */
    suspend fun getSelfAbnormalCount(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineNotifyWebService.getSelfAbnormalCount(map)
    }

    /**
     * 消息全部已读
     */
    suspend fun allRead(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineNotifyWebService.allRead(request)
    }

    /**
     * 预警处理
     */
    suspend fun handleWarning(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineNotifyWebService.handleWarning(request)
    }

    /**
     * 采购异常明细列表
     */
    suspend fun getUnPurchase(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineNotifyWebService.getUnPurchase(map)
    }

    /**
     * 菜单明细列表
     */
    suspend fun getListByDate(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineNotifyWebService.getListByDate(map)
    }

    /**
     * 未留样明细列表
     */
    suspend fun getUnLeaveSample(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineNotifyWebService.getUnLeaveSample(map)
    }
}