package com.zhizhunbao.lib.common.net.service.local

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.MonthMenuBean
import com.zhizhunbao.lib.common.bean.ReserveListBean
import com.zhizhunbao.lib.common.net.constant.RESERVE_DETAIL_URL
import com.zhizhunbao.lib.common.net.constant.RESERVE_LIST_URL
import com.zhizhunbao.lib.common.net.constant.RESERVE_CREATE_URL
import com.zhizhunbao.lib.common.net.constant.RESERVE_UPDATE_URL
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * 本地菜单预定接口地址
 */
interface LocalReserveWebService {

    /**
     * 菜单预定创建
     */
    @POST(RESERVE_CREATE_URL)
    suspend fun reserveCreate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 菜单预定编辑
     */
    @POST(RESERVE_UPDATE_URL)
    suspend fun reserveUpdate(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 获取菜单预定列表
     */
    @GET(RESERVE_LIST_URL)
    suspend fun getReserveList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<MonthMenuBean>?>

    /**
     * 获取菜单预定详情
     */
    @GET(RESERVE_DETAIL_URL)
    suspend fun getReserveDetail(@QueryMap map: HashMap<String, Any?>): BaseResponse<ReserveListBean>

}