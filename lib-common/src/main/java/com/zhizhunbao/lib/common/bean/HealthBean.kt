package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 健康证信息
 */
@Parcelize
data class HealthBean(
    var id : String? = "",
    var name : String? = "",
    /**1: 男 ，2 ： 女*/
    var gender : Int? = 1,
    var certificateId : String? = "",
    var expirationDate : String? = "",
    var imageUrl : String? = "",
) : Parcelable {
    fun getExpirationTime() = expirationDate.safe()

    fun getSex() = when(gender) {
        1 -> "男"
        2 -> "女"
        else -> ""
    }
}