package com.zhizhunbao.lib.common.bean

import com.zhizhunbao.lib.common.ext.formatDecimal
import com.zhizhunbao.lib.common.ext.safe


/**
 * Created by xwh on 2020/3/27  星期五
 * description:智慧结算识别菜品转换bean
 */
data class RecognizeDishBean(
    var recognizeId: String = "-1",//识别号
    var recognizeDishId: String? = null, //原始识别菜品id
    var num: String = "1",//数量
    var name: String? = null,
    var price: Double? = null,
    var id: String? = null,//修改后的菜品id
    var rect: String? = null,//菜品对应的坐标
    var originName: String? = null,//原始后的菜品名
    var canDelete: Boolean? = false,//是否可以删除
    var discount: Float? = 10f,
    var classifyName: String = "",
    var dishIndex: Int = -1,
    var dishBarCode: String? = null,
    var flavorIds: String? = null,//口味id
    var flavorNames: String? = null,//口味名称
    var originImageUrl: String? = "",//原图
    var coverImageUrl: String? = "",
    var location: RecognizeResultBean.ItemBean.SubImageBean.LocationBean? = null,
    var type: RecognizeResultBean.TypeBean? = null,
) {

    fun getPriceString() = price.safe().formatDecimal(2, false)

    fun getDiscountString() = discount.safe().formatDecimal(1, true)

    fun getFlavorIsShow() = !flavorNames.isNullOrBlank()
}
