package com.zhizhunbao.lib.common.net.service.online

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.BusinessLicenseBean
import com.zhizhunbao.lib.common.bean.HealthBean
import com.zhizhunbao.lib.common.bean.SelfCheckBean
import com.zhizhunbao.lib.common.net.constant.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * 每日自检接口
 */
interface OnlineCheckWebService {

    /**
     * 自检日期查询
     */
    @GET(SELF_CHECK_DATE_URL)
    suspend fun getSelfCheckDate(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<String>?>

    /**
     * 自检信息新增
     */
    @POST(SELF_CHECK_SAVE_URL)
    suspend fun createSelfCheckDate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 自检信息查询
     */
    @GET(SELF_CHECK_INFO_URL)
    suspend fun getSelfCheckInfo(@QueryMap map: HashMap<String, Any?>): BaseResponse<SelfCheckBean?>

}