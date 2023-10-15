package com.zhizhunbao.lib.common.bean


/**
 * Created by sagiller on 2020/3/10  星期二
 * description: 触发日志
 */
data class TriggerLogDTO(
    var duration: Long,
    var total: Long,
    var time: Long,
    var content: String,
) : JsonConverter() {


    companion object {
        fun addLog(list:MutableList<TriggerLogDTO>, content: String) {
            val currentTime = System.currentTimeMillis()
            var duration = 0L
            var total = 0L
            if (list.size != 0) {
                duration = currentTime - list.last().time
                total = currentTime - list.first().time
            }
            list.add(TriggerLogDTO(duration, total, currentTime, content))
        }
    }
}

