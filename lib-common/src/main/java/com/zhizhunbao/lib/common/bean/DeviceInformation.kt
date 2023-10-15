package com.zhizhunbao.lib.common.bean

import com.zhizhunbao.lib.common.dialog.SingleChoiceBean

/**
 * 蓝牙设备
 */
open class DeviceInformation(
    name: String? = null,
    var address: String? = null
) : SingleChoiceBean(name)