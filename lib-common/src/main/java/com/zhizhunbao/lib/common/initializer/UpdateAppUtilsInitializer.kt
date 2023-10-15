package com.zhizhunbao.lib.common.initializer

import android.content.Context
import androidx.startup.Initializer
import update.UpdateAppUtils

/**
 * UpdateAppUtils初始化
 */
class UpdateAppUtilsInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        UpdateAppUtils.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}