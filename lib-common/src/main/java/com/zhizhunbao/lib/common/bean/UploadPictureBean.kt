package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 上传图片的返回结果
 */
@Parcelize
data class UploadPictureBean(
    var uuid :String?,
    var imageUrl :String?,
    var id :String?,
    var url :String?
) : Parcelable