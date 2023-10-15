package com.zhizhunbao.lib.common.net.service.online

import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.bean.AccountBean
import com.zhizhunbao.lib.common.bean.DiningListBean
import com.zhizhunbao.lib.common.net.constant.ACCOUNT_URL
import com.zhizhunbao.lib.common.net.constant.DINING_INFO_URL
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 本地菜品接口地址
 */
interface OnlineDishWebService {

    /**
     * 获取用户信息
     */
    @GET(ACCOUNT_URL)
    suspend fun getAccountInfo(@Query("id") companyId: String): BaseResponse<AccountBean?>

    /**
     * 获取餐厅信息
     */
    @GET(DINING_INFO_URL)
    suspend fun getDiningInfo(): BaseResponse<DiningListBean>
}