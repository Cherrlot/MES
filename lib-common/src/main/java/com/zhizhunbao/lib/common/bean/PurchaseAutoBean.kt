package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.util.TimeUtils
import kotlinx.parcelize.Parcelize

/**
 * description:采购数据自动生成的数据bean
 */
@Parcelize
class PurchaseAutoBean(
    var id: String? = null,
    var name: String? = "",
) : Parcelable {
}