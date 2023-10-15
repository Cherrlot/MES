package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * description: 检查更新
 */
@Parcelize
data class UpdateBean(
    var id: String?,
    var version: String?,
    var description: String?,
    var url: String?,
    var releaseDate: Int?,
    var forcedUpgrade: Boolean?,
    var Memo: String?,
    var Val: String?,
    // status=normal或force当强制更新标识
    var Status: String?,
): Parcelable {
    fun  getForcedUpgrade() = Status?.safe("normal") != "normal"
}