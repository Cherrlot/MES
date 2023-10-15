package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.UPLOAD_PIC_URL
import com.zhizhunbao.lib.common.net.constant.UPLOAD_PORT
import com.zhizhunbao.lib.common.net.constant.UPLOAD_URL
import com.zhizhunbao.lib.common.net.service.local.LocalDishWebService
import com.zhizhunbao.lib.common.net.service.online.OnlineDishWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * 菜品数据仓库
 */
class DishRepository(
    private val onlineDishWebService: OnlineDishWebService,
    private val localDishWebService: LocalDishWebService
) {

    /**
     * 获取用户信息
     */
    suspend fun getAccountInfo() = withContext(Dispatchers.IO) {
        onlineDishWebService.getAccountInfo(AppLocalData.userId)
    }
    /**
     * 修改菜品排序
     */
    suspend fun modifyDishSort(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.modifyDishSort(request)
    }
    /**
     * 获取拍照菜品分析信息
     */
    suspend fun getRecognizeData(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.getRecognizeData(request)
    }

    /**
     * 获取餐厅信息
     */
    suspend fun getDiningInfo() = withContext(Dispatchers.IO) {
        onlineDishWebService.getDiningInfo()
    }

    /**
     * 获取餐厅菜品
     */
    suspend fun getDishList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        localDishWebService.getDishList(map)
    }

    /**
     * 移除菜品
     */
    suspend fun setDishRemove(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.setDishRemove(request)
    }
    /**
     * 一键上下架菜品
     */
    suspend fun setDishUpOrDown(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.setDishUpOrDown(request)
    }

    /**
     * 新增菜品
     */
    suspend fun setDishCreate(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.setDishCreate(request)
    }

    /**
     * 编辑菜品
     */
    suspend fun setDishModify(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.setDishModify(request)
    }

    /**
     * 菜品分类管理排序
     */
    suspend fun sortDishType(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.sortDishType(
            request
        )
    }

    /**
     * 菜品分类删除
     */
    suspend fun deleteDishType(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.deleteDishType(
            request
        )
    }

    /**
     * 菜品分类创建
     */
    suspend fun createDishType(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.createDishType(
            request
        )
    }

    /**
     * 修改菜品分类
     */
    suspend fun modifyDishType(request: RequestBody) = withContext(Dispatchers.IO) {
        localDishWebService.modifyDishType(
            request
        )
    }

    /**
     * 菜品分类管理列表数据
     */
    suspend fun getDishManagerTypeList() = withContext(Dispatchers.IO) {
        localDishWebService.getDishManagerTypeList(AppLocalData.diningInfo?.id.safe())
    }

    /**
     * 获取标准菜
     */
    suspend fun getStandardDishByName(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        localDishWebService.getStandardDishByName(map)
    }

    /**
     * 上传图片
     */
    suspend fun upload(
        part: MultipartBody.Part, dir: String? = null, keyId: String,
        type: Int, metadata: String? = null
    ) = withContext(Dispatchers.IO) {
        localDishWebService.upload(
            BuildConfig.BASE_URL + UPLOAD_URL,
            part,
            dir,
            keyId,
            type,
            metadata
        )
    }
}