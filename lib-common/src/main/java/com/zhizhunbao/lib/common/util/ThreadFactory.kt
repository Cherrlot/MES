package com.zhizhunbao.lib.common.util

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ThreadFactory private constructor() {
    private var singleThreadPool: ExecutorService? = null
    private var fixThreadPool: ExecutorService? = null

    init {
        singleThreadPool = Executors.newSingleThreadExecutor()
        fixThreadPool = Executors.newFixedThreadPool(7)
    }

    companion object {
        private var sThreadFactory: ThreadFactory? = null

        fun instance(): ThreadFactory? {
            if (sThreadFactory == null) {
                synchronized(ThreadFactory::class) {
                    if (sThreadFactory == null) {
                        sThreadFactory =
                            ThreadFactory()
                    }
                }
            }
            return sThreadFactory
        }
    }

    fun getSingleThreadPool() = singleThreadPool

    fun getFixThreadPool() = fixThreadPool
}