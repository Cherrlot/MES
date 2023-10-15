package com.zhizhunbao.lib.common.event

/**
 * Created by xwh on 2020/7/24  星期五
 * description:客户端连接服务器状态
 */
class SocketConnectionStatusEvent(var connectionInterrupt: Boolean = false, var initStatus: Boolean = false)