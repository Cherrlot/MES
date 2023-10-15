package com.zhizhunbao.lib.common.net.service.local

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.ReserveListBean
import com.zhizhunbao.lib.common.net.constant.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * 本地菜单留样接口地址
 */
interface LocalRetentionWebService {

    /**
     * 菜单留样
     */
    @POST(RETENTION_URL)
    suspend fun retention(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 查询未留样的记录
     */
    @GET(UN_RETENTION_LIST_URL)
    suspend fun getUnRetentionList(@QueryMap map: HashMap<String, Any?>): BaseResponse<ReserveListBean>

}