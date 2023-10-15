package com.zhizhunbao.lib.common.websocket

import android.os.Handler
import android.os.Looper
import com.jeremyliao.liveeventbus.LiveEventBus
import com.zhizhunbao.lib.common.constant.BUS_REFRESH_OPTION
import com.zhizhunbao.lib.common.constant.BUS_REFRESH_WORK_ORDER
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit


object AppWebsocket {
    private var mWebSocket: WebSocket? = null

    var mIsConnect = false

    private val mHandler = Handler(Looper.getMainLooper())

    private val mClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
//            .pingInterval(15, TimeUnit.SECONDS) // 设置 PING 帧发送间隔
            .build()
    }

    fun appWebsocketConnect() {
        val location = "ws://zhizhunbao.51dingdian.com:7000/websocket?token=${AppLocalData.token}"
        val request = Request.Builder()
            .url(location)
            .addHeader("Origin", "http://zhizhunbao.51dingdian.com:7000")
            .build()
        mWebSocket = mClient.newWebSocket(request, WsListener())
    }

    /**
     * 回调
     */
    internal class WsListener : WebSocketListener() {
        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            //连接关闭...
            AppLog.e("AppWebsocket连接关闭！$code$reason")
            // 断线重连
            mHandler.postDelayed({
                appWebsocketConnect()
            } , 10 * 1000)
            mIsConnect = false
            mWebSocket = null
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            // 出错了
            AppLog.e("AppWebsocket 连接错误！错误信息：" + t.message)
            mIsConnect = false
            // 断线重连
            mHandler.postDelayed({
                appWebsocketConnect()
            } , 10 * 1000)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            // 收到服务端发送来的 String 类型消息
            AppLog.i("AppWebsocket收到消息:$text")
            handleMsg(text)
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            val msg = bytes.string(Charset.forName("UTF-8"))
            AppLog.i("AppWebsocket收到bytes消息:$msg")
            handleMsg(msg)
        }

        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            AppLog.i("AppWebsocket 连接成功")
            //连接成功...
            mWebSocket = webSocket
            //发送机器编号
            send(AppLocalData.machineNo)
            mIsConnect = true
        }
    }

    private fun handleMsg(msg: String) {
        if (msg.contains("refresh")) {
            // 刷新工单列表,和进去工单列表的明细信息
            LiveEventBus.get(BUS_REFRESH_WORK_ORDER).post(true)
            LiveEventBus.get(BUS_REFRESH_OPTION).post(true)
        } else if (msg.contains("print")) {
            // 蓝牙打印
        }
    }

    /**
     * WS发送消息
     *
     * @param message
     */
    fun send(message: String) {
        if (mWebSocket != null) {
            val result = mWebSocket?.send(message)
            AppLog.i("AppWebsocket 发送消息：$message ${if (result.safe()) "成功" else "失败"}")
        }
    }

}