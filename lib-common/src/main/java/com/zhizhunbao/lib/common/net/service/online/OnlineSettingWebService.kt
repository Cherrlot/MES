package com.zhizhunbao.lib.common.net.service.online

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.MachineBean
import com.zhizhunbao.lib.common.bean.RoleBean
import com.zhizhunbao.lib.common.bean.SortingBean
import com.zhizhunbao.lib.common.bean.StaffBean
import com.zhizhunbao.lib.common.bean.UnitBean
import com.zhizhunbao.lib.common.bean.UserCreateBean
import com.zhizhunbao.lib.common.bean.UserListBean
import com.zhizhunbao.lib.common.net.constant.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface OnlineSettingWebService {

    /**
     * 获取用户列表
     */
    @GET(USER_LIST_URL)
    suspend fun getUserList(): BaseResponse<MutableList<UserListBean>>

    /**
     * 用户修改
     */
    @POST(USER_MODIFY_URL)
    suspend fun modifyUser(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 用户创建一
     */
    @POST(USER_CREATE_URL)
    suspend fun createUser(@Body request: RequestBody): BaseResponse<UserCreateBean>
    /**
     * 用户删除
     */
    @POST(USER_DELETE_URL)
    suspend fun deleteUser(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 重置用户密码
     */
    @POST(USER_RESET_PWD_URL)
    suspend fun resetPwd(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 添加餐厅
     */
    @POST(DINING_ADD_URL)
    suspend fun addDining(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 编辑餐厅
     */
    @POST(DINING_MODIFY_URL)
    suspend fun modifyDining(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 删除餐厅
     */
    @POST(DINING_DELETE_URL)
    suspend fun deleteDining(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 餐厅启用
     */
    @POST(DINING_ENABLE_URL)
    suspend fun enableDining(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 餐厅停用
     */
    @POST(DINING_DISABLE_URL)
    suspend fun disableDining(@Body request: RequestBody): BaseResponse<Any?>
    /**
     * 修改密码
     */
    @POST(EDIT_PWD_URL)
    suspend fun editPwd(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 新增人员
     */
    @POST(STAFF_ADD_URL)
    suspend fun addStaff(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 删除人员
     */
    @POST(STAFF_DELETE_URL)
    suspend fun deleteStaff(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 修改人员
     */
    @POST(STAFF_MODIFY_URL)
    suspend fun modifyStaff(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 新增单位
     */
    @POST(UNIT_ADD_URL)
    suspend fun addUnit(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 删除单位
     */
    @POST(UNIT_DELETE_URL)
    suspend fun deleteUnit(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 修改单位
     */
    @POST(UNIT_MODIFY_URL)
    suspend fun modifyUnit(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 获取人员列表
     */
    @GET(ROLE_LIST_URL)
    suspend fun getRoleList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<StaffBean>?>

    /**
     * 获取设备列表
     */
    @GET(DEVICE_LIST_URL)
    suspend fun getDeviceList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<MachineBean>?>

    /**
     * 获取人员详情
     */
    @GET(ROLE_DETAIL_URL)
    suspend fun getRoleDetail(@QueryMap map: HashMap<String, Any?>): BaseResponse<StaffBean?>

    /**
     * 获取角色列表
     */
    @GET(ROLE_TYPE_LIST_URL)
    suspend fun getRoleTypeList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<RoleBean>?>

    /**
     * 获取单位列表
     */
    @GET(UNIT_LIST_URL)
    suspend fun getUnitList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<UnitBean>?>

    /**
     * 新增供应商
     */
    @POST(PROVIDER_ADD_URL)
    suspend fun addProvider(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 删除供应商
     */
    @POST(PROVIDER_DELETE_URL)
    suspend fun deleteProvider(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 修改供应商
     */
    @POST(PROVIDER_MODIFY_URL)
    suspend fun modifyProvider(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 获取供应商列表
     */
    @GET(PROVIDER_LIST_URL)
    suspend fun getProviderList(@QueryMap map: HashMap<String, Any?>): BaseResponse<SortingBean?>
}