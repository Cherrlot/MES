package com.zhizhunbao.lib.common.net.service.online

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.BusinessLicenseBean
import com.zhizhunbao.lib.common.bean.DiningListBean
import com.zhizhunbao.lib.common.bean.HealthBean
import com.zhizhunbao.lib.common.net.constant.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * 各种证件接口
 */
interface OnlinePaperWebService {

    /**
     * 营业执照查询
     */
    @GET(BUSINESS_LICENSE_URL)
    suspend fun getBusinessLicense(@QueryMap map: HashMap<String, Any?>): BaseResponse<DiningListBean?>

    /**
     * 营业执照新增
     */
    @POST(BUSINESS_LICENSE_SAVE_URL)
    suspend fun saveBusinessLicense(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 营业执照修改
     */
    @POST(BUSINESS_LICENSE_UPDATE_URL)
    suspend fun updateBusinessLicense(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 健康证查询
     */
    @GET(HEALTH_URL)
    suspend fun getHealthList(@QueryMap map: HashMap<String, Any?>): BaseResponse<DiningListBean?>

    /**
     * 健康证新增
     */
    @POST(HEALTH_CREATE_URL)
    suspend fun createHealth(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 健康证修改
     */
    @POST(HEALTH_MODIFY_URL)
    suspend fun modifyHealth(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 健康证删除
     */
    @POST(HEALTH_DELETE_URL)
    suspend fun deleteHealth(@Body request: RequestBody): BaseResponse<Any?>

}