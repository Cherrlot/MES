package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.formatDecimal
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * description:预定时间bean
 */
@Parcelize
class ReserveTimeBean(
    var id: String? = null,
    /**
     * 提前天数
     */
    var DayCount: Int? = null,
    /**
     * 提前时间：时分
     */
    var Time: String? = "",
) : Parcelable {

    fun getTimeString(): String {
        if (Time.isNullOrBlank() || Time.safe("000000").length < 6) {
            return "00:00"
        }
        return "${Time.safe("000000").substring(0, 2)}:${
            Time.safe("000000").substring(2, 4)
        }"
    }

}