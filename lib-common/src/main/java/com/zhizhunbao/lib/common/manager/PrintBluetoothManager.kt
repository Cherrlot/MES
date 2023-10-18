package com.zhizhunbao.lib.common.manager

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.zhizhunbao.lib.common.CommonApplication
import com.zhizhunbao.lib.common.bean.DeviceInformation
import com.zhizhunbao.lib.common.dialog.SingleChoiceDialog
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.util.NetumUtil
import com.zhizhunbao.lib.common.util.ThreadFactory
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.UnsupportedEncodingException
import java.lang.ref.WeakReference
import java.util.UUID
import java.util.Vector

/**
 * 蓝牙打印机
 */
object PrintBluetoothManager {
    val mTscMode = byteArrayOf(0x1f, 0x1b, 0x1f, 0xfc.toByte(), 0x01, 0x02, 0x03, 0x33)
    val mCpclMode = byteArrayOf(0x1f, 0x1b, 0x1f, 0xfc.toByte(), 0x01, 0x02, 0x03, 0x44)
    val mEscMode = byteArrayOf(0x1f, 0x1b, 0x1f, 0xfc.toByte(), 0x01, 0x02, 0x03, 0x55)
    val mSelftest =
        byteArrayOf(0x1f, 0x1b, 0x1f, 0x93.toByte(), 0x10, 0x11, 0x12, 0x15, 0x16, 0x17, 0x10, 0x00)

    private val mDatas: MutableList<DeviceInformation> = mutableListOf()

    private var mBluetoothAdapter: BluetoothAdapter? = null
    private var mBluetoothReceiver: BroadcastReceiver? = null //用于接收蓝牙状态改变广播的广播接收者

    private var mBluetoothStateReceiver: BroadcastReceiver? = null
    private var mDevice: BluetoothDevice? = null
    private var mBluetoothSocket: BluetoothSocket? = null
    private val mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") //蓝牙串口服务的UUID
    private var mOutputStream: OutputStream? = null
    /**
     * 是否开始读数
     */
    private var mIsStart = false

    /**
     * 是否断开线程, false: 退出线程
     */
    private var mIsRead = true

    private var mThread : ReceiveDataThread? = null

    private var mDialog: WeakReference<SingleChoiceDialog>? = null

    private var mResultListener: ((String)-> Unit)? = null

    private var mOnErrorListener: ((String)-> Unit)? = null

    private var mOnStatusChangeListener: BluetoothStatusChangeListener? = null

    fun builder(dialog: SingleChoiceDialog): PrintBluetoothManager {
        mIsRead = true

        mDialog = WeakReference(dialog)

        if (mBluetoothAdapter != null && mBluetoothSocket?.isConnected.safe()) {
            // 链接已经打开
            return this
        }

        initReceiver()

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter != null) {
            //判断蓝牙是否打开并可见
            if (!(mBluetoothAdapter?.isEnabled.safe())) {
                //请求打开并可见
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                CommonApplication.instance.startActivity(intent)
            } else {
                discoverBluetooth()
            }
//            mIsStart = true
        } else {
            mIsStart = false
            "设备不支持蓝牙功能".toast()
        }
        return this
    }

    fun addStatusChangeListener(listener: BluetoothStatusChangeListener): PrintBluetoothManager {
        mOnStatusChangeListener = listener
        return this
    }

    fun startRead(resultListener: ((String)-> Unit)?, onError: ((String)-> Unit)?) {
        mResultListener = resultListener
        mOnErrorListener = onError
        mIsStart = true

        if (!mBluetoothSocket?.isConnected.safe()) {
            if (!mDialog?.get()?.isShowing().safe()) {
                mDialog?.get()?.show()
            }
        }
    }

    fun stopRead() {
        mIsStart = false
    }

    /**
     **注册广播接收者
     */
    private fun initReceiver() {
        //创建用于接收蓝牙状态改变广播的广播接收者
        mBluetoothStateReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
                    BluetoothAdapter.STATE_ON -> {
                        "蓝牙已打开".toast()
                        discoverBluetooth()
                    }

                    BluetoothAdapter.STATE_OFF -> "蓝牙已关闭".toast()
                    BluetoothAdapter.STATE_TURNING_ON -> "蓝牙正在打开".toast()
                    BluetoothAdapter.STATE_TURNING_OFF -> "蓝牙正在关闭".toast()
                }
            }
        }
        //创建设备扫描广播接收者
        mBluetoothReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.action
                if (BluetoothDevice.ACTION_FOUND == action) {
                    AppLog.i("搜索到设备")
                    var isAdded = false //标记扫描到的设备是否已经在数据列表里了
                    //获取扫描到的设备
                    val device =
                        intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    //保存设备的信息
                    val deviceInformation = DeviceInformation(device?.name, device?.address)
                    for (data in mDatas) {
                        //判断已保存的设备信息里是否有一样的
                        if (data.address.equals(deviceInformation.address)) {
                            isAdded = true
                            break
                        }
                    }
                    if (!isAdded && !deviceInformation.name.isNullOrBlank()) {
                        //通知UI更新
                        mDatas.add(deviceInformation)
                        AppLog.i("添加设备--device: ${deviceInformation.name}")
                        if (mDialog?.get()?.isShowing().safe()) {
                            mDialog?.get()?.addDatas(mDatas)
                        }
                    }
                }
            }
        }
        //注册广播接收者
        val filter1 = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        val filter2 = IntentFilter(BluetoothDevice.ACTION_FOUND)
        CommonApplication.instance.registerReceiver(mBluetoothStateReceiver, filter1)
        CommonApplication.instance.registerReceiver(mBluetoothReceiver, filter2)
    }

    fun cancelDiscovery() {
        if (mBluetoothAdapter?.isDiscovering.safe()) {
            mBluetoothAdapter?.cancelDiscovery()
        }
    }

    /**
     * 获取设备信息
     */
    fun getDevicesInfo(index: Int) : DeviceInformation {
        return mDatas[index]
    }

    /**
     * 与目标设备建立连接
     */
    fun connectDevice(index: Int) {
        cancelDiscovery()

        //通过地址拿到该蓝牙设备device
        mDevice = mBluetoothAdapter?.getRemoteDevice(mDatas[index].address)
        ThreadFactory.instance()?.getSingleThreadPool()?.execute {
            try {
                //建立socket通信
                mBluetoothSocket = mDevice?.createRfcommSocketToServiceRecord(mUUID)
                mBluetoothSocket?.connect()
                if (mBluetoothSocket?.isConnected.safe()) {
                    "连接成功".toast()
                    //开启接收数据的线程
                    mThread = ReceiveDataThread(mBluetoothSocket)
                    mThread?.start()
                    mOutputStream = mBluetoothSocket?.outputStream
                    mOnStatusChangeListener?.onConnect()
                } else {
                    "连接失败，结束重进".toast()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                "蓝牙连接失败，请确认蓝牙设备蓝牙打开，且没有被其他设备连接".toast()
                try {
                    mBluetoothSocket?.close()
                } catch (ioException: IOException) {
                    ioException.printStackTrace()
                }
            }
        }
    }

    /**
     * 搜索蓝牙设备
     */
    private fun discoverBluetooth() {
        // 将已配对的设备添加到列表中
        val pairedDevices = mBluetoothAdapter?.bondedDevices
        if (pairedDevices?.size.safe() > 0) {
            for (device in pairedDevices!!) {
                val data = DeviceInformation(device?.name, device?.address)
                mDatas.add(data)
            }
        }

        if (!mDialog?.get()?.isShowing().safe()) {
            mDialog?.get()?.addDatas(mDatas)
            mDialog?.get()?.show()
        }

        cancelDiscovery()
        //搜索设备
        mBluetoothAdapter?.startDiscovery()
        "正在搜索设备".toast()
    }

    fun onDestroy() {
        try {
            mIsRead = false
            mIsStart = false
            if (mBluetoothSocket?.isConnected.safe()) {
                //关闭socket
                mBluetoothSocket?.close()
                mBluetoothAdapter = null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            AppLog.e(e.localizedMessage.safe())
        }
    }
    private fun convertVectorByteToBytes(data: Vector<Byte>): ByteArray {
        val sendData = ByteArray(data.size)
        if (data.size > 0) {
            for (i in data.indices) {
                sendData[i] = data[i] as Byte
            }
        }
        return sendData
    }
    fun sendCommand(command: Vector<Byte>) {
        AppLog.d("发送指令")
//        val command: Vector<Byte> = Vector<Byte>()
//        val bs: ByteArray?
//        if (content.isNotBlank()) {
//            try {
//                bs = content.toByteArray()
//                for (i in bs.indices) {
//                    command.add(bs[i])
//                }
                mOutputStream?.write(convertVectorByteToBytes(command), 0 ,command.size)
                mOutputStream?.flush()
//            } catch (var4: UnsupportedEncodingException) {
//                var4.printStackTrace()
//            }
//        }
    }

    /**
     * 修改模式
     * @param mode 使用[mCpclMode], [mCpclMode], [mEscMode]
     */
    fun changeMode(mode: ByteArray) {
        val data: Vector<Byte> = Vector<Byte>(mode.size)
        for (i in mode.indices) {
            data.add(mode[i])
        }
        mOutputStream?.write(convertVectorByteToBytes(data), 0 ,data.size)
        mOutputStream?.flush()
    }

    /**
     * 打印自检
     */
    fun selfTest() {
        val data: Vector<Byte> = Vector<Byte>(mTscMode.size)
        for (i in mSelftest.indices) {
            data.add(mSelftest[i])
        }
        mOutputStream?.write(convertVectorByteToBytes(data), 0 ,data.size)
        mOutputStream?.flush()
    }

    /**
     * 负责接收数据的线程
     */
    class ReceiveDataThread(bluetoothSocket: BluetoothSocket?) : Thread() {
        private var inputStream: InputStream? = null

        init {
            try {
                //获取连接socket的输入流
                inputStream = bluetoothSocket?.inputStream
            } catch (e: IOException) {
                mOnErrorListener?.invoke("读数失败，请退出当前页面后重试")
            }
        }

        override fun run() {
            super.run()
            typeOne()
        }

        private fun typeOne() {
            try{
                var bytes: Int
                val buffer = ByteArray(256)
                while (mIsRead) {
                    try {
                        if (inputStream?.read(buffer).safe().also {
                                bytes = it
                            } > 0) {
                            val bufData = ByteArray(bytes)
                            for (i in 0 until bytes) {
                                bufData[i] = buffer[i]
                            }
                            AppLog.e("printer read buffer: ${bufData.toString()}")
                            if (bufData.isNotEmpty()) {
                                val result = getScanGunResult(bufData)
                                if (!result.isNullOrBlank())
                                    mOnErrorListener?.invoke(result)
                            }
                        }
                    } catch (e: java.lang.Exception) {
                        onDestroy()
                        mOnErrorListener?.invoke("打印机连接中断，请重新连接打印机后尝试")
                        mOnErrorListener = null
                    }
                }
            } catch (e: Exception) {
                onDestroy()
                mOnErrorListener?.invoke("打印机连接中断，请重新连接打印机后尝试")
                mOnErrorListener = null
            }
        }

        /**
         * 将ASCII转为条形码
         */
        private fun getScanGunResult(bufData: ByteArray): String? {
            val end = bufData[bufData.size - 1].toInt()
            if (end != 13 || bufData.size <= 2)
                return null
            val sb = StringBuffer()
            for (element in bufData) {
                val data = element.toInt()
                if (data == 13)
                    break
                else if (data >= 48){
                    sb.append(data - 48)
                }
            }
            AppLog.d("result为：$sb")
            return sb.toString()
        }
    }
}
