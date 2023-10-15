package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 看板数据
 */
@Parcelize
data class BoardBean(
    var id: String? = null,
    var Customer: String? = null,
    var Product: String? = null,
    var Orderno: String? = null,
    var Descr: String? = null,
    var Plan: Int? = null,
    var Finish: Int? = null,
    /**
     * 如果是Created为红色
     * Doing为绿色
     * Released为黄色
     * Done为蓝色
     */
    var Status: String? = null,
) : Parcelable {
    fun getFinishString() = Finish.safe(0).toString().plus("/")
    fun getPlanString() = Plan.safe(1).toString()
}