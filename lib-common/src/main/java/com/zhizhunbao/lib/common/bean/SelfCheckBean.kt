package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 每日自检
 */
@Parcelize
data class SelfCheckBean(
    var id: String?,
    var date: String?,
    var images: MutableList<SelfInspectionImages>?,
    var items: MutableList<SelfInspectionItem>?,
) : Parcelable

@Parcelize
data class SelfInspectionItem(
    var name: String? = "",
    /** 0: false, 1: true **/
    var value: String? = "0",
) : Parcelable

@Parcelize
data class SelfInspectionImages(
    var id: String? = "",
    var SelfInspectionId: String?,
    var url: String?,
) : Parcelable