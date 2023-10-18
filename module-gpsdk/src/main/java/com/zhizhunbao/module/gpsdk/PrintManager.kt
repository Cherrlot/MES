package com.zhizhunbao.module.gpsdk

import android.graphics.BitmapFactory
import com.jeremyliao.liveeventbus.LiveEventBus
import com.tools.command.EscCommand
import com.tools.command.LabelCommand
import com.zhizhunbao.lib.common.constant.BUS_RECONNECT_PRINTER
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.tool.string
import java.io.UnsupportedEncodingException
import java.util.Vector


object PrintManager {
    val mTscMode = byteArrayOf(0x1f, 0x1b, 0x1f, 0xfc.toByte(), 0x01, 0x02, 0x03, 0x33)
    val mCpclMode = byteArrayOf(0x1f, 0x1b, 0x1f, 0xfc.toByte(), 0x01, 0x02, 0x03, 0x44)
    val mEscMode = byteArrayOf(0x1f, 0x1b, 0x1f, 0xfc.toByte(), 0x01, 0x02, 0x03, 0x55)
    val mSelftest =
        byteArrayOf(0x1f, 0x1b, 0x1f, 0x93.toByte(), 0x10, 0x11, 0x12, 0x15, 0x16, 0x17, 0x10, 0x00)

    /**
     * 修改模式
     * @param mode 使用[mCpclMode], [mCpclMode], [mEscMode]
     */
    fun changeMode(mode: ByteArray) {
        val data: Vector<Byte> = Vector<Byte>(mode.size)
        for (i in mode.indices) {
            data.add(mode[i])
        }
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].sendDataImmediately(data)
    }

    fun sendCo() {

    }
    /**
     * 打印自检
     */
    fun selfTest() {
//        val data: Vector<Byte> = Vector<Byte>(mTscMode.size)
//        for (i in mSelftest.indices) {
//            data.add(mSelftest[i])
//        }
//        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].sendDataImmediately(data)
        val tsc = LabelCommand()
        /* 设置标签尺寸，按照实际尺寸设置 */tsc.addSize(40, 30)
//        /* 设置标签间隙，按照实际尺寸设置，如果为无间隙纸则设置为0 */tsc.addGap(0)
        /* 设置打印方向 */tsc.addDirection(
            LabelCommand.DIRECTION.BACKWARD,
            LabelCommand.MIRROR.NORMAL
        )
        /* 设置原点坐标 */tsc.addReference(0, 0)
            /* 清除打印缓冲区 */tsc.addCls()
        /* 绘制简体中文 */tsc.addText(
            0,
            0,
            LabelCommand.FONTTYPE.SIMPLIFIED_CHINESE,
            LabelCommand.ROTATION.ROTATION_0,
            LabelCommand.FONTMUL.MUL_1,
            LabelCommand.FONTMUL.MUL_1,
            "Welcome to use SMARNET printer"
        )
        /* 绘制简体中文 */tsc.addText(
            0,
            30,
            LabelCommand.FONTTYPE.SIMPLIFIED_CHINESE,
            LabelCommand.ROTATION.ROTATION_0,
            LabelCommand.FONTMUL.MUL_1,
            LabelCommand.FONTMUL.MUL_1,
            "欢迎使用"
        )

        /* 打印标签 */tsc.addPrint(1, 1)
//        /* 打印标签后 蜂鸣器响 */tsc.addSound(2, 100)
        val datas = tsc.command
        /* 发送数据 */
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0] == null) {
            return
        }
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].sendDataImmediately(datas)
    }

    /**
     * 打印标签
     */
    fun printContent(content: String) {
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0] == null ||
            !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].connState
        ) {
            R.string.str_cann_printer.string.toast()
            LiveEventBus.get(BUS_RECONNECT_PRINTER).post(true)
            return
        }
        ThreadPool.getInstantiation().addTask {
            val command: Vector<Byte> = Vector<Byte>()
            val bs: ByteArray?
            if (content.isNotBlank()) {
                try {
                    bs = content.toByteArray()
                    for (i in bs.indices) {
                        command.add(bs[i])
                    }
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].sendDataImmediately(command)
                } catch (var4: UnsupportedEncodingException) {
                    var4.printStackTrace()
                }
            }
        }
    }
}