package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.ext.formatDecimal
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * description:商品bean
 *
 */
@Parcelize
data class ProductBean(
    var id: String? = null,
    var name: String? = null,
    var spu: String? = null,
    var isWeigh: Boolean? = null,
    var quantity: Float? = null,
    var categoryId: String? = null,
    var image: String? = null,
    var skus: MutableList<MaterialBean>? = null,
) : Parcelable {

    fun getImageUrl() = if(image.isNullOrBlank()) "" else BuildConfig.BASE_URL.plus("/").plus(image)

    fun getNameAndUnit() = skus?.get(0)?.name.safe().plus("(${skus?.get(0)?.unit.safe()})")

    fun getNum() = skus?.get(0)?.getNumWithUnit()

    fun getPriceString() = skus?.get(0)?.price.safe(0f).formatDecimal(2, false)
    fun getPrice() = skus?.get(0)?.price.safe(0f)
}