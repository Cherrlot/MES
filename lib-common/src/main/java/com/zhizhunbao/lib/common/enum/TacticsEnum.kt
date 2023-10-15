package com.zhizhunbao.lib.common.enum

/**
 * Created by xwh on 2020/5/15  星期五
 * description:识别策略
 */
enum class TacticsEnum(val index: Int, val tactics: String) {
    SINGLE(0, "single"),
    AVERAGE(1, "average"),
    VOTE(2, "vote")
}