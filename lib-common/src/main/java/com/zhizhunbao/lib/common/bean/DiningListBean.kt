package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.tool.string
import kotlinx.parcelize.Parcelize

/**
 * description:餐厅列表bean
 *
 */
@Parcelize
class DiningListBean(
    var id: String? = null,
    var diningName: String? = null,
    var diningAddr: String? = "",
    var companyId: String? = "",
    var LongitudeAndLatitude: String? = "",
    var longitude: String? = "",
    var latitude: String? = "",
    var logo: String? = "",
    /** 菜品接口地址 **/
    var ip: String? = "",
    /** 是否使用公司支付通道 false: 需要输入商户id和支付方式 */
    var useCompanyChannel: Boolean? = true,
    /** 是否使用人脸支付 */
    var useFaceSettlement: Boolean? = false,
    /**  商户id */
    var merchantsId: String? = null,
    /**  0: 联动支付 */
    var settlementChannel: Int? = 0,
    /** 1：启用， 2：停用 */
    var status: Int? = 1,
    /** 1：本地算法， 2：线上算法， 3：不使用算法 */
    var algorithm: Int? = 1,
    /** 0：不需要特殊处理； 1：工行版本； 2：中控支付 **/
    var specialSettlement: Int? = 0,
    var licence: BusinessLicenseBean? = null,
    var healthCerts: MutableList<HealthBean>? = null,
    var mealTimes: MutableList<LimitBean>? = null,
): Parcelable {
//    fun getStatusString(): String{
//        return if (status.safe(2) == 1)
//            com.zhizhunbao.lib.common.R.string.member_stop.string
//        else
//            com.zhizhunbao.lib.common.R.string.member_start.string
//    }
    fun getStatusResult(): Boolean{
        return status.safe(2) == 1
    }
}