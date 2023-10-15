package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * {"result":{"Cdate":"2020-10-12 16:49:38",
 * "Co":"巨杰",
 * "Descr":"管理员",
 * "Id":8,
 * "Name":"jjadmin",
 * "Qrcode":"",
 * "Role":"admin",
 * "Status":"",
 * "Uaccount":"",
 * "Udate":"2020-10-21 10:01:03",
 * "Uname":"",
 * "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbyI6IuW3qOadsCIsImV4cCI6MTY5MTM3Mjk5NSwiaWF0IjoxNjkxMTEzNzk1LCJpZCI6IjgiLCJyb2xlIjoiYWRtaW4iLCJ1c2VyIjoiamphZG1pbiJ9.pRae0YvrFcKc2DUYniacblrvlo0TfX6xiE4-XiDTqtg"}}
 */
@Parcelize
data class UserBean(
    var token: String?,
    var Co: String?,
    var Descr: String?,
    var Id: String?,
    var Name: String?,
    var Qrcode: String?,
    var Role: String?,
    var Status: String?,
    var Uaccount: String?,
    var Udate: String?,
    var Uname: String?,
    var authId: String?,
    var userId: String?,
    var companyId: String?
) : Parcelable