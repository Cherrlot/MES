package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 菜单预定
 */
@Parcelize
class ReserveDataBean(
    var limitBean: LimitBean? = null,
    var dishList: MutableList<DishBean>? = null,
): Parcelable