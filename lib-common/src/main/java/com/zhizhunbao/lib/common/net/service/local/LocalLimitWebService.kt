package com.zhizhunbao.lib.common.net.service.local

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.LimitBean
import com.zhizhunbao.lib.common.net.constant.LIMIT_CREATE_URL
import com.zhizhunbao.lib.common.net.constant.LIMIT_DELETE_URL
import com.zhizhunbao.lib.common.net.constant.LIMIT_LIST_URL
import com.zhizhunbao.lib.common.net.constant.LIMIT_MODIFY_URL
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 本地消费限额接口地址
 */
interface LocalLimitWebService {

    /**
     * 消费限额列表查询
     */
    @GET(LIMIT_LIST_URL)
    suspend fun getLimitList(@Query("CompanyId") CompanyID: String): BaseResponse<MutableList<LimitBean>>

    /**
     * 消费限额创建
     */
    @POST(LIMIT_CREATE_URL)
    suspend fun limitCreate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 消费限额删除
     */
    @POST(LIMIT_DELETE_URL)
    suspend fun limitDelete(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 消费限额编辑
     */
    @POST(LIMIT_MODIFY_URL)
    suspend fun limitModify(@Body request: RequestBody): BaseResponse<Any?>
}