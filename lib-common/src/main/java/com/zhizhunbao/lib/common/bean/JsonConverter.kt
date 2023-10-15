package com.zhizhunbao.lib.common.bean

import com.google.gson.Gson

open class JsonConverter {

    companion object {

        fun <T> fromJson(json: String, clazz: Class<T>): T {
            return Gson().fromJson(json, clazz)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}