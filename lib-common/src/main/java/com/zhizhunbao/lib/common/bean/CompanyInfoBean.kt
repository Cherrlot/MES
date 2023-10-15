package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 公司信息
 */
@Parcelize
data class CompanyInfoBean(
//    var user: CompanyUserBean? = null,
    var Name: String? ="",
    var Descr: String? ="",
    var Co: String? = null,
): Parcelable {
//    fun getCompanyName() = user?.getCompanyName().safe()
//    fun getDiningRoomId() = user?.getDiningRoomId().safe()
//    fun getCompanyId() = user?.getCompanyId().safe()
}

@Parcelize
data class CompanyUserBean(
    var id: String? ="",
    var name: String? ="",
    var nickname: String? ="",
    var headImage: String? = null,
    var auths: MutableList<AuthsBean>? = null,
    var resources: MutableList<RoleBean>? = null,
): Parcelable {
    fun getCompanyName() = getAuthData()?.companyName.safe()
    fun getDiningRoomId() = getAuthData()?.diningId.safe()
    fun getCompanyId() = getAuthData()?.companyId.safe()

    private fun getAuthData() : AuthsBean? {
        auths?.forEach {
            if (it.kind.safe() == 2) {
                return it
            }
        }
        return null
    }
}

@Parcelize
data class AuthsBean(
    var diningId: String? = "",
    var companyId: String? = "",
    var companyName: String? = "",
    var kind: Int? = null,
): Parcelable

/**
 * 权限
 */
@Parcelize
data class RoleBean(
    var name: String? = "",
    var description: String? = "",
    var id: String? = "",
    var kind: Int? = null,
    var resources: MutableList<ResourceBean>? = null,
): Parcelable

/**
 * 权限
 */
@Parcelize
data class ResourceBean(
    var name: String? = "",
    var description: String? = "",
    var id: String? = "",
): Parcelable
