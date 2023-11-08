package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.tool.string
import kotlinx.parcelize.Parcelize

/**
 * description:设备信息
 *
 */
@Parcelize
class MachineBean(
    var Id: String? = "",
    var No: String? = "",
    var Workplace: String? = "",
): Parcelable