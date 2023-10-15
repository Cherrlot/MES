package com.zhizhunbao.lib.common.jpush

import cn.jpush.android.api.JPushInterface
import com.zhizhunbao.lib.common.CommonApplication

object PushUtil {

    /**
     * 清除推送通知角标
     */
    fun setNum(num: Int) {
        JPushInterface.setBadgeNumber(CommonApplication.instance, num)
    }
}