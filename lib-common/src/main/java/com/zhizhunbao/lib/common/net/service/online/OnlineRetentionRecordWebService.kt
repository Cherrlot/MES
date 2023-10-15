package com.zhizhunbao.lib.common.net.service.online

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.RetentionBean
import com.zhizhunbao.lib.common.net.constant.RETENTION_DELETE_URL
import com.zhizhunbao.lib.common.net.constant.RETENTION_DETAIL_URL
import com.zhizhunbao.lib.common.net.constant.RETENTION_LIST_URL
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * 本地菜单留样记录接口地址
 */
interface OnlineRetentionRecordWebService {

    /**
     * 菜单留样记录列表
     */
    @GET(RETENTION_LIST_URL)
    suspend fun retentionList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<RetentionBean>?>

    /**
     * 菜单留样记录详情
     */
    @GET(RETENTION_DETAIL_URL)
    suspend fun retentionDetail(@QueryMap map: HashMap<String, Any?>): BaseResponse<RetentionBean?>

    /**
     * 菜单留样记录详情删除
     */
    @POST(RETENTION_DELETE_URL)
    suspend fun retentionDetailDelete(@Body request: RequestBody): BaseResponse<Any?>

}