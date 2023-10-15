package com.zhizhunbao.lib.common.base

open class BaseResponse<T>(
    var result: T,
//    var result: Boolean,
    var message: String?,
    var code: Int)