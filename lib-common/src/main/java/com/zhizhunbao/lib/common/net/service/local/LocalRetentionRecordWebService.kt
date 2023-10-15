package com.zhizhunbao.lib.common.net.service.local

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.net.constant.RETENTION_DETAIL_URL
import com.zhizhunbao.lib.common.net.constant.RETENTION_LIST_URL
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * 本地菜单留样记录接口地址
 */
interface LocalRetentionRecordWebService {

    /**
     * 菜单留样记录列表
     */
    @GET(RETENTION_LIST_URL)
    suspend fun retentionList(@QueryMap map: HashMap<String, Any?>): BaseResponse<Any?>

    /**
     * 菜单留样记录详情
     */
    @GET(RETENTION_DETAIL_URL)
    suspend fun retentionDetail(@Body request: RequestBody): BaseResponse<Any?>

}