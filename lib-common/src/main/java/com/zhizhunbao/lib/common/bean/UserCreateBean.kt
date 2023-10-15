package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * description:用户创建bean
 *
 */
@Parcelize
data class UserCreateBean(
    var ID: String? = null,
): Parcelable