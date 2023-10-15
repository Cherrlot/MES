package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 菜品类型
 */
@Parcelize
data class DishTypeBean(
    var id: String? = null,
    var dishLibraryId: Long? = 0,
    var diningId: Long? = 0,
    var name: String? = null,
    var headerUrl: String? = null,
    var serialNumber: String? = null,
    var version: Int? = 0,
    var isDefault: Boolean? = false,
    var orderIndex: Int? = 0,
): Parcelable