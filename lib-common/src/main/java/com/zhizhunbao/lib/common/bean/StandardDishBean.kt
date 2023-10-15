package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * description:标准菜bean
 */
@Parcelize
class StandardDishListBean(
    var standardDishes: MutableList<StandardDishBean>?,
): Parcelable

@Parcelize
class StandardDishBean(
    var id: String?,
    var dishName: String?,
): Parcelable