package com.zhizhunbao.lib.common.util

import android.os.Handler
import android.os.Looper
import android.util.Log

object UIThreadHelper {
    private val TAG = UIThreadHelper::class.java.simpleName

    private val sUIHandler = Handler(Looper.getMainLooper())

    private val sUiThread = sUIHandler.looper.thread

    fun runOnUiThread(task: Runnable) {
        if (Thread.currentThread() !== sUiThread) {
            sUIHandler.post(task)
        } else {
            try {
                task.run()
            } catch (e: Exception) {
                Log.w(TAG, e)
            }
        }
    }

    fun runOnUiThread(task: Runnable, duration: Long) {
        if (duration > 0 || Thread.currentThread() !== sUiThread) {
            sUIHandler.postDelayed(task, duration)
        } else {
            try {
                task.run()
            } catch (e: Exception) {
                Log.w(TAG, e)
            }
        }
    }

    fun removeFromUiThread(task: Runnable) {
        sUIHandler.removeCallbacks(task)
    }
}