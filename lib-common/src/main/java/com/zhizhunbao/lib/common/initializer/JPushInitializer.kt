package com.zhizhunbao.lib.common.initializer

import android.content.Context
import androidx.startup.Initializer
import cn.jpush.android.api.JPushInterface
import com.zhizhunbao.lib.common.BuildConfig

/**
 * 极光推送初始化
 */
class JPushInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.PRINT_LOG) {
            JPushInterface.setDebugMode(true)
        }
        JPushInterface.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}