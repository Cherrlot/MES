package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.formatDecimal
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * description:菜品限额bean
 */
@Parcelize
class LimitBean(
    var id: String? = null,
    var name: String? = null,
    /**
     * 开始时间
     */
    var startTime: String? = "",
    /**
     * 结束时间
     */
    var endTime: String? = "",
    /**
     * 限额
     */
    var limit: String? = "0",
    /**
     * 预约就餐人数
     */
    var number: String? = "0",
) : Parcelable {
    /**
     * 就餐人数
     */
    fun getNumString() = number.safe("0")
    /**
     * 就餐人数
     */
    fun getNum() = "${number.safe("0")}人就餐"
    /**
     * 限额
     */
    fun getMoneyString() = limit.safe("0").toFloat().formatDecimal(2, false)

    fun getLimitTime() = "${getStartString()}~${getEndString()}"

    fun getStartString(): String {
        if (startTime.isNullOrBlank() || startTime.safe("000000").length < 6) {
            return "00:00"
        }
        return "${startTime.safe("000000").substring(0, 2)}:${
            startTime.safe("000000").substring(2, 4)
        }"
    }

    fun getEndString(): String {
        if (endTime.isNullOrBlank() || endTime.safe("235959").length < 6) {
            return "23:59:59"
        }
        return "${endTime.safe("235959").substring(0, 2)}:${
            endTime.safe("235959").substring(2, 4)
        }"
    }
}