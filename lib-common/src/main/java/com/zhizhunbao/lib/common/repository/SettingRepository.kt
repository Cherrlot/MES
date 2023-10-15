package com.zhizhunbao.lib.common.repository

import com.zhizhunbao.lib.common.net.service.online.OnlineSettingWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

/**
 * 设置Repository
 */
class SettingRepository(private val onlineSettingWebService: OnlineSettingWebService)  {

    /**
     * 修改密码
     */
    suspend fun editPwd(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.editPwd(request)
    }

    /**
     * 新增人员
     */
    suspend fun addStaff(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.addStaff(request)
    }

    /**
     * 删除人员
     */
    suspend fun deleteStaff(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.deleteStaff(request)
    }

    /**
     * 修改人员
     */
    suspend fun modifyStaff(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.modifyStaff(request)
    }

    /**
     * 新增单位
     */
    suspend fun addUnit(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.addUnit(request)
    }

    /**
     * 删除单位
     */
    suspend fun deleteUnit(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.deleteUnit(request)
    }

    /**
     * 修改单位
     */
    suspend fun modifyUnit(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.modifyUnit(request)
    }

    /**
     * 获取设备列表
     */
    suspend fun getDeviceList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineSettingWebService.getDeviceList(map)
    }

    /**
     * 获取人员详情
     */
    suspend fun getRoleDetail(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineSettingWebService.getRoleDetail(map)
    }

    /**
     * 获取角色列表
     */
    suspend fun getRoleTypeList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineSettingWebService.getRoleTypeList(map)
    }

    /**
     * 获取单位列表
     */
    suspend fun getUnitList(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineSettingWebService.getUnitList(map)
    }

    /**
     * 新增供应商
     */
    suspend fun addProvider(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.addProvider(request)
    }

    /**
     * 删除供应商
     */
    suspend fun deleteProvider(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.deleteProvider(request)
    }

    /**
     * 修改供应商
     */
    suspend fun modifyProvider(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.modifyProvider(request)
    }

    /**
     * 获取供应商列表
     */
    suspend fun getSortingDetail(map: HashMap<String, Any?>) = withContext(Dispatchers.IO) {
        onlineSettingWebService.getProviderList(map)
    }
    /**
     * 获取用户列表
     */
    suspend fun getUserList() = withContext(Dispatchers.IO) {
        onlineSettingWebService.getUserList()
    }

    /**
     * 用户创建一
     */
    suspend fun createUser(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.createUser(request)
    }

    /**
     * 用户修改
     */
    suspend fun modifyUser(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.modifyUser(request)
    }

    /**
     * 用户删除
     */
    suspend fun deleteUser(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.deleteUser(request)
    }
    /**
     * 重置用户密码
     */
    suspend fun resetPwd(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.resetPwd(request)
    }

    /**
     * 获取餐厅列表
     */
    suspend fun addDining(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.addDining(request)
    }

    /**
     * 编辑餐厅
     */
    suspend fun modifyDining(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.modifyDining(request)
    }

    /**
     * 删除餐厅
     */
    suspend fun deleteDining(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.deleteDining(request)
    }

    /**
     * 餐厅启用
     */
    suspend fun enableDining(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.enableDining(request)
    }

    /**
     * 餐厅停用
     */
    suspend fun disableDining(request: RequestBody) = withContext(Dispatchers.IO) {
        onlineSettingWebService.disableDining(request)
    }
}