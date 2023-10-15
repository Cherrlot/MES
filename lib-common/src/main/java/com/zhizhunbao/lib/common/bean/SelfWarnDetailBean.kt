package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelfWarnDetailBean(
    var name: String? = null,
    var content: String? = null,
    /** true：蓝色 false：橙色 **/
    var contentColor: Boolean? = null,
): Parcelable
