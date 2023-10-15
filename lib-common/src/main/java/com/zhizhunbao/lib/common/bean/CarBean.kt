package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * description:购物车
 *
 */
@Parcelize
data class CarBean(
    var quantity: Float? = null,
    var price: Float? = null,
    var productList: MutableList<ProductBean>? = null,
): Parcelable