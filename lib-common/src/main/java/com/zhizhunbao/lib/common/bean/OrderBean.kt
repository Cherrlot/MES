package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 订单
 */
@Parcelize
data class OrderBean(
    var id: String? = null,
    var customerId: String? = null,
    var customerName: String? = null,
    var phone: String? = null,
    var address: AddressBean? = null,
    var date: String? = null,
    var quantity: Float? = null,
    var weight: Float? = null,
    var itemAmount: Float? = null,
    var packageFee: Float? = null,
    var shippingFee: Float? = null,
    var discount: Float? = null,
    var couponAmount: Float? = null,
    var total: Float? = null,
    var status: Int? = null,
    var items: MutableList<MaterialBean>? = null,
) : Parcelable {
    fun getOrderState(): String {
        return when(status.safe()) {
            3 -> "采购中"
            4 -> "已拣货"
            6 -> "发货中"
            7 -> "配送完成"
            8 -> "订单取消"
            9 -> "订单完成"
            else -> ""
        }
    }
}