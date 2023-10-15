package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.luck.picture.lib.entity.LocalMedia
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderingFileStorageBean(
    /** 本地图片实体类*/
    var localMedia: LocalMedia? = null,
    var itemId: String? = "",
    var id: String? = "",
    var uid: String? = "",
    var url: String? = ""//分割图片Url
) : Parcelable
