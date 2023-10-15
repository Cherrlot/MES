package com.zhizhunbao.lib.common.initializer

import android.content.Context
import androidx.startup.Initializer
import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.net.adapterModule
import com.zhizhunbao.lib.common.net.localDishNetModule
import com.zhizhunbao.lib.common.net.netModule
import com.zhizhunbao.lib.common.net.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

/**
 * Koin start up初始化
 */
class KoinInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        // 初始化 Koin
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(context)
            modules(listOf(netModule, localDishNetModule, repositoryModule, adapterModule))
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}