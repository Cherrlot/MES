package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.formatDecimal
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 菜品数据
 */
@Parcelize
data class DishBean(
    var id: String? = null,
    var tenant: Int? = 0,
    var version: Int? = null,
    var status: Int? = null,
    var name: String? = null,
    var standardDishId: String? = null,
    var weight: String? = null,
    var pinyin: String? = null,
    var firstLetters: String? = null,
    var price: Double? = null,
    var isEnabled: Boolean? = null,
    var serialNumber: String? = null,
    var departmentId: String? = null,
    var diningId: Long? = null,
    var diningRoomId: Long? = null,
    var typeId: String? = null,
    var unitId: String? = null,
    /**
     * 外卖预定
     */
    var isDelivery: Boolean? = null,// 外卖预定
    /**
     * 外卖价格
     */
    var deliveryPrice: String? = null,
    /**
     * 包装费用
     */
    var packingPrice: String? = null,
    /**
     * 菜品外送时间
     */
    var periods: String? = null,
    /**
     * 是否推荐
     */
    var isRecommend: Boolean? = false,
    /**
     * 每日限量(份)
     */
    var amount: Int? = 0,
    /**
     * 识别结算
     */
    var isSmartSettlement: Boolean? = null,// 识别结算
    /**
     * 普通结算
     */
    var isNormalSettlement: Boolean? = null,// 普通结算
    /**
     * 金额结算
     */
    var isAmountSettlement: Boolean? = null,// 金额结算
    var isExtra: Boolean? = null,//是否是附加分类
    var clusters: MutableList<ClusterBean>? = null,//图片聚类
    var images: MutableList<OrderingFileStorageBean>? = null,//分割图
    var displayImages: String? = null,
    var headerUrl: String? = null,
    var originImages: MutableList<OrderingFileStorageBean>? = null,//原图
    var orderIndex: Int? = 0,
    var barCode: String? = null,//商品条码
    var itemType: DishTypeBean? = null,// 菜品类型
    var probability: String? = null,
    var coverImageUrl: String? = null,
) : Parcelable {
    fun getProbabilityString(): String{
        return probability.safe("0").toFloat().formatDecimal(2, false)
    }
    fun getDisplayUrl(): MutableList<OrderingFileStorageBean> {
        if (headerUrl.isNullOrBlank())
            return mutableListOf()
        return mutableListOf(OrderingFileStorageBean(url = headerUrl))
    }
}
