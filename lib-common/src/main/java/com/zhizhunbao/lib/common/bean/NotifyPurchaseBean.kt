package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotifyPurchaseBean(
    var good_id: String? = null,

    var good_name: String? = null,
    var income: String? = null,

    var outcome: String? = null,

    /**正值为超过， 负值为不够*/
    var balance: Float? = null,
): Parcelable
