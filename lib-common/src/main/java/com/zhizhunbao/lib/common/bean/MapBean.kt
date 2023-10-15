package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.tool.string
import kotlinx.parcelize.Parcelize

/**
 * description:地图bean
 *
 */
@Parcelize
class MapBean(
    var address: String? = "",
    var name: String? = "",
    var latitude: Double? = 0.00,
    var longitude: Double? = 0.00,
): Parcelable