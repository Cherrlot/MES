package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClusterBean(
    var id: String? = null,

    var insertTime: String? = null,
    var updateTime: String? = null,

    var delFlag: Boolean? = null,

    var images: String? = null,
    var dishLibraryId: Long? = null,

    var dishId: String? = null //菜品id
): Parcelable
