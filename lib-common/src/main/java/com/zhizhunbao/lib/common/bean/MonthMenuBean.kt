package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 每日菜单-哪天有数据
 */
@Parcelize
class MonthMenuBean(
    var id: String? = null,
    var date: String? = null,
): Parcelable