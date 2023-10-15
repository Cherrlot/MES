package com.zhizhunbao.lib.common.event

import android.graphics.Bitmap
import com.zhizhunbao.lib.common.bean.TriggerLogDTO
import com.zhizhunbao.lib.common.bean.VersionDTO

/**
 * Created by xwh on 2020/7/8  星期三
 * description:
 */
class ClientSocketEvent(
    var isInit: Boolean = false,
    var bitmap: Bitmap? = null,
    var isTrigger: Boolean = true,
    var logList: MutableList<TriggerLogDTO>? = null,
    var version: VersionDTO? = null
)