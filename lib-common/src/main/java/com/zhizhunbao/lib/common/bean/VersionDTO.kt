package com.zhizhunbao.lib.common.bean


/**
 * Created by sagiller on 2020/3/10  星期二
 * description: 版本信息
 */
data class VersionDTO(
    var serverVersionName: String? = null,
    var serverVersionCode: Int? = null,
    var clientVersionName: String? = null,
    var clientVersionCode: Int? = null
) : JsonConverter()

