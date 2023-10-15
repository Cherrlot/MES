package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.util.TimeUtils
import kotlinx.parcelize.Parcelize

/**
 * 消息
 */
@Parcelize
data class MessageBean(
    var id: String? = null,
    var employeeId: String? = null,
    var title: String? = null,
    var content: String? = null,
    var createdAt: String? = null,
    var isReader: Boolean? = false,
) : Parcelable {
    fun getReadMark() = !isReader.safe()

    fun getDate() = TimeUtils.changeDateFormat(
        TimeUtils.DATE_FORMAT_11,
        TimeUtils.DATE_FORMAT_2,
        createdAt.safe()
    )
}