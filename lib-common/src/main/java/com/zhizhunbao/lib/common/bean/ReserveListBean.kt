package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 菜单预定
 */
@Parcelize
class ReserveListBean(
    var id: String? = null,
    var date: String? = null,
    var items: MutableList<MenuItemBean>? = null,
): Parcelable

@Parcelize
class MenuItemBean(
    var id: String? = null,
    var itemId: String? = null,
    var itemMenuId: String? = null,
    var payLimitId: String? = null,
    var payLimitName: String? = null,
    var isLeaveSample: Boolean? = null,
    var item: DishBean? = null,
): Parcelable