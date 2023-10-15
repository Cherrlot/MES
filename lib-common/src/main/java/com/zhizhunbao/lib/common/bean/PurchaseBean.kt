package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.util.TimeUtils
import kotlinx.parcelize.Parcelize

/**
 * description:采购数据bean
 */
@Parcelize
class PurchaseBean(
    var id: String? = null,
    var createdAt: String? = "",
    var userId: String? = "0",
    var purchaseTime: String? = "0",
    var userName: String? = "0",
    var date: String? = "0",
    var items: MutableList<PurchaseItemBean>? = null,
) : Parcelable {
    /**
     * 时间
     */
    fun getTimeString() = TimeUtils.getCreateDate(purchaseTime)
}

@Parcelize
class PurchaseItemBean(
    var id: String? = null,
    var itemId: String? = null,
    var itemName: String? = null,
    var quantity: Float? = null,
    var price: Float? = null,
    /**采购价格**/
    var priceBuy: Float? = 0f,
    var unitId: String? = null,
    var unitName: String? = null,
    var providerId: String? = null,
    var providerName: String? = null,
    var imageUrl: String? = null,
    /**是否是编辑之前的信息, false: 新增， true编辑**/
    var isEdit: Boolean? = false,
    var materialBean: MaterialBean? = null,
): Parcelable {
    fun getUnit() = materialBean?.unit.safe("")
    fun getNum() = materialBean?.getNum()
}