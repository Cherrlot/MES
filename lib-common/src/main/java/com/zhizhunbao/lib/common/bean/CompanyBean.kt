package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 单位信息
 *
 * 	"data": {
 * 		"id": "1632993253991124992",
 * 		"createdAt": "2023-03-07T14:35:05.957546+08:00",
 * 		"phone": "13333333333",
 * 		"nickname": "xxx食堂",
 * 		"name": "张三",
 * 		"isBusiness": true,
 * 		"addresses": [{
 *     "id": "111",
 *     "customerId": "1632993253991124992",
 *     "telephone": "1333333333",
 *     "country": "中国",
 *     "state": "浙江",
 *     "city": "杭州",
 *     "district": "西湖区",
 *     "street": "详细街道",
 *     "fulladdr": "风尚小区12栋0305",
 *     "isDefault": true
 *   }]
 * 	}
 */
@Parcelize
data class CompanyBean(
    var id: String? = null,
    /**
     * 公司名
     */
    var nickname: String? = null,
    var headImage: String? = null,
    /**
     * 用户名
     */
    var name: String? = null,
    var isFreeze: Boolean? = null,
    var isBusiness: Boolean? = null,
    var status: Int? = null,
    var phone: String? = null,
    var addresses: MutableList<AddressBean>? = null,
    var contacts: MutableList<AccountBean>? = null,
) : Parcelable

@Parcelize
data class AddressBean(
    var id: String? = null,
    var customerId: String? = null,
    var telephone: String? = null,
    var contact: String? = null,
    var country: String? = null,
    var state: String? = null,
    var city: String? = null,
    var district: String? = null,
    var street: String? = null,
    var fulladdr: String? = null,
) : Parcelable {
    fun getAddress() = state.safe().plus(city.safe()).plus(district.safe()).plus(street.safe()).plus(fulladdr.safe())
}