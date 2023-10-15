package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.util.TimeUtils
import kotlinx.parcelize.Parcelize

/**
 * description:菜品留样记录bean
 */
@Parcelize
class RetentionBean(
    var id: String? = null,
    var payLimitId: String? = null,
    var payLimitName: String? = "",
    var createdAt: String? = "",
    var userId: String? = "0",
    var userName: String? = "0",
    var date: String? = "0",
    var imageUrl: String? = "0",
    var items: MutableList<RetentionItemBean>? = null,
) : Parcelable {
    /**
     * 时间
     */
    fun getTimeString() = TimeUtils.getCreateDate(createdAt)
}

@Parcelize
class RetentionItemBean(
    var id: String? = null,
    var LeaveSamplesId: String? = null,
    var itemId: String? = null,
    var itemImage: String? = null,
    var itemName: String? = null,
    var rect: String? = null,
): Parcelable