package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * description:菜品分类管理bean
 *
 * {
"result": true,
"message": "",
"data": [
{
"ID": "1457647137079693312",
"CreatedAt": "2021-11-08T17:52:13.912+08:00",
"UpdatedAt": "2021-11-08T17:52:13.933+08:00",
"DeletedAt": null,
"Tenant": 0,
"Name": "晚上好",
"DishLibraryId": 48,
"DiningId": 0,
"OrderIndex": 1,
"Version": 0
},
{
"ID": "1457944046923157504",
"CreatedAt": "2021-11-09T13:32:02.735+08:00",
"UpdatedAt": "2021-11-09T13:32:02.735+08:00",
"DeletedAt": null,
"Tenant": 0,
"Name": "你好",
"DishLibraryId": 48,
"DiningId": 33,
"OrderIndex": 2,
"Version": 0
}
]
}
 */
@Parcelize
class DishManagerTypeBean(
    var id: String? = null,
    var name: String? = null,
    var serialNumber: String? = "",
    var total: String? = "0",
    var version: String? = "",
    var orderIndex: Int? = 0,
): Parcelable {
    fun getCount() = total.safe("0")
}