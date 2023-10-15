package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 营业执照
 */
@Parcelize
data class BusinessLicenseBean(
    var id : String?,
    var region: String?,
    var licenseName: String?,
    var diningName: String?,
    var address: String?,
    var area: String?,
    var operator: String?,
    var phone: String?,
    var note: String?,
    var operationMode: Int?,
    var booking: Boolean?,
    var licensePath: String?,
    ) : Parcelable