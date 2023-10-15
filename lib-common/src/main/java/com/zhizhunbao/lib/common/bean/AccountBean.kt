package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 登录用户
 */
@Parcelize
data class AccountBean(
    var id: String? = null,
    /**
     * 公司名
     */
    var nickname: String? = null,
    var headImage: String? = null,
    var customerId: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var role: String? = null,
    var isFreeze: Boolean? = null,
    var status: Int? = null,
) : Parcelable