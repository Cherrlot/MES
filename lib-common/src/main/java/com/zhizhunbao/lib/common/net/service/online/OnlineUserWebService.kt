package com.zhizhunbao.lib.common.net.service.online

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.BoardBean
import com.zhizhunbao.lib.common.bean.OptionBean
import com.zhizhunbao.lib.common.bean.UpdateBean
import com.zhizhunbao.lib.common.bean.UserBean
import com.zhizhunbao.lib.common.net.constant.BOARD_URL
import com.zhizhunbao.lib.common.net.constant.CHECK_UPDATE_APP_URL
import com.zhizhunbao.lib.common.net.constant.JPUSH_URL
import com.zhizhunbao.lib.common.net.constant.LOGIN_URL
import com.zhizhunbao.lib.common.net.constant.LOGOUT_URL
import com.zhizhunbao.lib.common.net.constant.OPTION_URL
import com.zhizhunbao.lib.common.net.constant.SCAN_QRCODE_URL
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface OnlineUserWebService {
    /**
     * 登录
     */
    @POST(LOGIN_URL)
    suspend fun login(@Body request: RequestBody): BaseResponse<UserBean>

    /**
     * 极光推送
     */
    @POST(JPUSH_URL)
    suspend fun addJPush(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 退出登录
     */
    @POST(LOGOUT_URL)
    suspend fun logout(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 版本检查更新
     */
    @GET(CHECK_UPDATE_APP_URL)
    suspend fun checkAppUpdate(@Query("Key") type: String): BaseResponse<MutableList<UpdateBean>?>

    /**
     * 获取看板数据
     */
    @GET(BOARD_URL)
    suspend fun getBoardList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<BoardBean>?>

    /**
     * 获取操作数据
     */
    @POST(OPTION_URL)
    suspend fun getOptionList(@Body request: RequestBody): BaseResponse<MutableList<OptionBean>?>

    /**
     * 获取工单列表
     */
    @GET(OPTION_URL)
    suspend fun getWorkOrderList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<OptionBean>?>

    /**
     * 扫码
     */
    @GET(SCAN_QRCODE_URL)
    suspend fun scanQrcode(@QueryMap map: HashMap<String, Any?>): BaseResponse<Any?>
}