package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 人员
 */
@Parcelize
data class StaffBean(
    var id: String? = null,
    var phone: String? = null,
    var title: String? = null,
    var name: String? = null,
    var roleId: String? = null,
    var role: RoleBean? = null,
) : Parcelable