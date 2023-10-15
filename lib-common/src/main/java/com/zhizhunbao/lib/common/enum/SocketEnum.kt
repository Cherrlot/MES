package com.zhizhunbao.lib.common.enum

/**
 * Created by xwh on 2020/5/26  星期二
 * description:服务器发送类型
 */
enum class SocketEnum(val type: Int, val alias: String) {
    /**
     * 服务端心跳包
     */
    HEART_SERVER(0, "HEART_SERVER"),

    /**
     * 服务端识别到餐盘
     */
    RECOGNIZE_PLATE(1, "RECOGNIZE_PLATE"),

    /**
     * 服务端未识别到餐盘
     */
    NONE_RECOGNIZE_PLATE(2, "NONE_RECOGNIZE_PLATE"),

    /**
     * 服务端拍照识别
     */
    CREATE_PHOTO(3, "CREATE_PHOTO"),

    /**
     * 服务端重新开始拍照识别
     */
    RECOGNIZE_AGAIN(4, "RECOGNIZE_AGAIN"),

}

