package com.zhizhunbao.lib.common.initializer

import android.content.Context
import androidx.startup.Initializer
import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.log.AppLog
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class LogInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        // 初始化 Logger 日志打印
        val strategy = PrettyFormatStrategy.newBuilder()
            .tag(BuildConfig.PRINT_LOG_TAG)
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(strategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.PRINT_LOG
            }
        })

        // base库输出日志
        AppLog.logEnable(BuildConfig.PRINT_LOG)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}