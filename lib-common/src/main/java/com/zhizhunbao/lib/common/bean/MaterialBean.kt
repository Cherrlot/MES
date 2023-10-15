package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.ext.formatDecimal
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 原材料
 */
@Parcelize
data class MaterialBean(
    /**是否是需要称重的菜品, false: 不需要， true:需要**/
    var isWeigh: Boolean? = false,
    /**
     * skuId
     */
    var id: String? = null,
    var spuId: String? = null,
    var name: String? = null,
    /**
     * 出库的商品名称
     */
    var spu: String? = null,
    /**
     * 供应商id
     */
    var vendorId: String? = null,
    var image: String? = null,
    var url: String? = null,
    /**
     * 规格
     */
    var sku: String? = null,
    /**
     * 单位
     */
    var unit: String? = null,
    /**
     * 采购入库库存
     */
    var stock: Float? = null,
    /**
     * 订单数量
     */
    var order: Float? = null,
    /**
     * 4: 分拣完成
     */
    var status: Int? = null,
    /**
     * 入库数量
     */
    var incoming: Float? = null,
    var itemId: String? = null,
    /**
     * 代客下单库存/采购入库数量
     */
    var quantity: Float? = null,
    /**
     * 价格
     */
    var price: Float? = null,
    var costPrice: String? = null,
    var specialPrice: String? = null,
    var energy: String? = null,
    var water: String? = null,
    var protein: String? = null,
    var fat: String? = null,
    var carbs: String? = null,
    var vitaminB1: String? = null,
    var vitaminB2: String? = null,
    var NicotinicAcid: String? = null,
    var na: String? = null,
    var ca: String? = null,
    var fe: String? = null,
    var cholesterol: String? = null,
) : Parcelable {
    fun getNum() = quantity.safe(0f).formatDecimal(2,false)
    fun getNumWithUnit() = quantity.safe(0f).formatDecimal(2,false).plus("（").plus(unit.safe("")).plus("）")
    fun getStockString() = stock.safe(0f).formatDecimal(2,false).plus(unit.safe(""))

    fun getImageUrl() = if(image.isNullOrBlank()) "" else BuildConfig.BASE_URL.plus("/").plus(image)
}