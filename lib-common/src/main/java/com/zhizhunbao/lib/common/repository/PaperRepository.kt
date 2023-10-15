package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.online.OnlinePaperWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 各种证件接口Repository
 */
class PaperRepository(
    private val mOnlinePaperWebService: OnlinePaperWebService
) {
    /**
     * 营业执照查询
     */
    suspend fun getBusinessLicense(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        mOnlinePaperWebService.getBusinessLicense(map)
    }

    /**
     * 营业执照新增
     */
    suspend fun saveBusinessLicense(request: RequestBody) = withContext(Dispatchers.IO) {
        mOnlinePaperWebService.saveBusinessLicense(request)
    }

    /**
     * 营业执照修改
     */
    suspend fun updateBusinessLicense(request: RequestBody) = withContext(Dispatchers.IO) {
        mOnlinePaperWebService.updateBusinessLicense(request)
    }

    /**
     * 健康证查询
     */
    suspend fun getHealthList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        mOnlinePaperWebService.getHealthList(map)
    }

    /**
     * 健康证新增
     */
    suspend fun createHealth(request: RequestBody) = withContext(Dispatchers.IO) {
        mOnlinePaperWebService.createHealth(request)
    }

    /**
     * 健康证修改
     */
    suspend fun modifyHealth(request: RequestBody) = withContext(Dispatchers.IO) {
        mOnlinePaperWebService.modifyHealth(request)
    }

    /**
     * 健康证删除
     */
    suspend fun deleteHealth(request: RequestBody) = withContext(Dispatchers.IO) {
        mOnlinePaperWebService.deleteHealth(request)
    }
}