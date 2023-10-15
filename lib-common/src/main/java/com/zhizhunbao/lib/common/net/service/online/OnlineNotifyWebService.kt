package com.zhizhunbao.lib.common.net.service.online

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.*
import com.zhizhunbao.lib.common.net.constant.*
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * 自查通知接口地址
 */
interface OnlineNotifyWebService {

    /**
     * 消息列表
     */
    @GET(SELF_NOTIFY_LIST_URL)
    suspend fun getMessageList(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<MessageBean>?>

    /**
     * 自查通知数量
     */
    @GET(SELF_NOTIFY_COUNT_URL)
    suspend fun getSelfAbnormalCount(@QueryMap map: HashMap<String, Any?>): BaseResponse<CountBean?>

    /**
     * 消息全部已读
     */
    @POST(SELF_NOTIFY_READ_URL)
    suspend fun allRead(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 预警处理
     */
    @POST(WARNING_HANDLE_URL)
    suspend fun handleWarning(@Body request: RequestBody): BaseResponse<Any?>

    /**
     * 未留样明细列表
     */
    @GET(UN_LEAVE_SAMPLE_URL)
    suspend fun getUnLeaveSample(@QueryMap map: HashMap<String, Any?>): BaseResponse<ReserveListBean?>

    /**
     * 采购异常明细列表
     */
    @GET(UN_PURCHASE_URL)
    suspend fun getUnPurchase(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<NotifyPurchaseBean>?>

    /**
     * 菜单明细列表
     */
    @GET(UN_MENU_URL)
    suspend fun getListByDate(@QueryMap map: HashMap<String, Any?>): BaseResponse<MutableList<ReserveListBean>?>
}