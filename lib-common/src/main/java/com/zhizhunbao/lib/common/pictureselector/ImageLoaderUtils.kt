package com.zhizhunbao.lib.common.pictureselector

import android.app.Activity
import android.content.Context

import android.content.ContextWrapper




class ImageLoaderUtils {
    companion object{
        private fun isDestroy(activity: Activity?): Boolean {
            return if (activity == null) {
                true
            } else activity.isFinishing || activity.isDestroyed
        }

        fun assertValidRequest(context: Context): Boolean {
            if (context is Activity) {
                return !isDestroy(context)
            } else if (context is ContextWrapper) {
                if (context.baseContext is Activity) {
                    val activity = context.baseContext as Activity
                    return !isDestroy(activity)
                }
            }
            return true
        }

    }
}