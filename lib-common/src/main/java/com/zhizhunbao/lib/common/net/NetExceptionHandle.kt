package com.zhizhunbao.lib.common.net

import android.accounts.NetworkErrorException
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.jeremyliao.liveeventbus.LiveEventBus
import com.zhizhunbao.lib.common.R
import com.zhizhunbao.lib.common.bean.ApiErrorBean
import com.zhizhunbao.lib.common.constant.BUS_LOGIN
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.router.ROUTER_PATH_LOGIN
import com.zhizhunbao.lib.common.tool.string
import com.zhizhunbao.lib.common.util.AppManager
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetExceptionHandle {
    fun handleException(
        e: Throwable?,
        failed: ((String?, StateType) -> Unit)? = null
    ) {
        e?.let {
            var errorMsg = it.printStackTrace().toString().ifBlank { it.message }
            if (errorMsg.equals("kotlin.Unit"))
                errorMsg = it.message

            when (it) {
                is HttpException -> {
                    var msg: String
                    when (it.code()) {
                        400 -> {
                            msg = try {
                                val errorBean = Gson().fromJson(
                                    it.response()?.errorBody()?.charStream(),
                                    ApiErrorBean::class.java
                                )
                                errorBean.message ?: errorBean.msg.safe()
                            } catch (e: Exception) {
                                R.string.network_wrong.string
                            }
                        }
                        401, 403 -> {
                            msg = "登录失效，请登录"
                            LiveEventBus.get(BUS_LOGIN).post(true)
                        }
                        404 -> msg = R.string.not_found.string
                        else -> {
                            msg = it.response()?.errorBody()?.string().safe()
                            if (msg.isBlank()) {
                                msg = R.string.service_error.string
                            }

                        }
                    }
                    failed?.invoke("$msg  错误码：${it.code()}", StateType.ERROR)
                    AppLog.e(msg)
                }
                is ConnectException -> {
                    failed?.invoke("出错了： ${errorMsg.safe()}", StateType.ERROR)
                    AppLog.e(errorMsg.safe())
                }
                is SocketTimeoutException -> {
                    failed?.invoke("出错了： ${errorMsg.safe()}", StateType.ERROR)
                    AppLog.e(errorMsg.safe())
                }
                is NetworkErrorException -> {
                    failed?.invoke("出错了： ${errorMsg.safe()}", StateType.ERROR)
                    AppLog.e(errorMsg.safe())
                }
                is UnknownHostException -> {
                    failed?.invoke("出错了： ${errorMsg.safe()}", StateType.ERROR)
                    AppLog.e(errorMsg.safe())
                }
                else -> {
                    if (!errorMsg.safe().contains("Job was cancelled"))
                        failed?.invoke("出错了： ${errorMsg.safe()}", StateType.ERROR)
                    AppLog.e(errorMsg.safe())
                }
            }
        }
    }
}