package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * description:用户管理bean
 *
 */
@Parcelize
data class UserListBean(
    var id: String? = null,
    var name: String? = null,
    var nickname: String? = null,
    var headImage: String? = null,
    var companyId: String? = null,
    /** 1：启用， 2：停用 */
    var status: Int? = 1,
    var isFreeze: Boolean? = false,
    var auths: MutableList<AuthBean>? = null,
): Parcelable {
    fun getStatusResult(): Boolean{
        return status.safe(2) == 1
    }
    fun getNameString() : String {
        var result = "昵称:${nickname.safe().ifBlank { "--" }}"
        auths?.forEach {
            if (it.kind.safe() == 2) {
                result = if(it.identifier.isNullOrBlank()) result else "昵称:${nickname.safe().ifBlank { "--" }}  账号:${it.identifier.safe()}"
                return result
            }
        }
        return result
    }

    fun getNickName() : String {
        return "昵称: ${nickname.safe().ifBlank { "--" }}"
    }

    fun getAccount() : String {
        val result = "账号: --"
        auths?.forEach {
            if (it.kind.safe() == 2) {
                return  "账号: ${it.identifier.safe().ifBlank { "--" }}"
            }
        }
        return result
    }

    fun getAuthId() : String {
        auths?.forEach {
            if (it.kind.safe() == 2) {
                return it.id.safe()
            }
        }
        return ""
    }

    @Parcelize
    data class AuthBean(
        var id: String? = null,
        var identifier: String? = null,
        var companyName: String? = null,
        var lastLoginAt: String? = null,
        var kind: Int? = null,
        var diningId: String? = null,
    ): Parcelable
}