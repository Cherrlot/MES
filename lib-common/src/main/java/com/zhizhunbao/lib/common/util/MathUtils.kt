package com.zhizhunbao.lib.common.util

import java.math.BigDecimal
import java.text.DecimalFormat
import kotlin.experimental.and

/**
 * created by xwh on 2020/1/9  星期四
 * description:
 */
class MathUtils {

    companion object {

        /**
         * 加法
         */
        fun add(m1: String, m2: String): Double {
            val p1 = BigDecimal(m1)
            val p2 = BigDecimal(m2)
            return p1.add(p2).toDouble()
        }

        fun add(m1: Double, m2: Double): Double {
            val p1 = BigDecimal(m1.toString())
            val p2 = BigDecimal(m2.toString())
            return p1.add(p2).toDouble()
        }

        /**
         * 减法
         */
        fun sub(m1: String, m2: String): Double {
            val p1 = BigDecimal(m1)
            val p2 = BigDecimal(m2)
            return p1.subtract(p2).toDouble()
        }

        fun sub(m1: Double, m2: Double): Double {
            val p1 = BigDecimal(m1.toString())
            val p2 = BigDecimal(m2.toString())
            return p1.subtract(p2).toDouble()
        }

        /**
         * 乘法
         */
        fun mul(m1: String, m2: String): Double {
            val p1 = BigDecimal(m1)
            val p2 = BigDecimal(m2)
            return p1.multiply(p2).toDouble()
        }

        fun mul(m1: Double, m2: Double): Double {
            val p1 = BigDecimal(m1.toString())
            val p2 = BigDecimal(m2.toString())
            return p1.multiply(p2).toDouble()
        }

        /**
         * 除法
         */
        fun div(m1: String, m2: String, scale: Int): Double {
            val p1 = BigDecimal(m1)
            val p2 = BigDecimal(m2)
            return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
        }

        fun div(m1: Double, m2: Double, scale: Int): Double {
            val p1 = BigDecimal(m1.toString())
            val p2 = BigDecimal(m2.toString())
            return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
        }

        fun div(m1: Int, m2: Int, scale: Int): Double {
            val p1 = BigDecimal(m1.toString())
            val p2 = BigDecimal(m2.toString())
            return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
        }

        fun div(m1: Long, m2: Long, scale: Int): Double {
            val p1 = BigDecimal(m1.toString())
            val p2 = BigDecimal(m2.toString())
            return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).toDouble()
        }

        /**
         * 保留小数点后两位
         */
        fun formatToString(double: Double): String {
            val df = DecimalFormat("0.00")
            return df.format(double)
        }

        fun intToByteArray(i: Int): ByteArray {
            val result = ByteArray(4)
            // 由高位到低位
            result[0] = (i shr 24 and 0xFF).toByte()
            result[1] = (i shr 16 and 0xFF).toByte()
            result[2] = (i shr 8 and 0xFF).toByte()
            result[3] = (i and 0xFF).toByte()
            return result
        }

        fun byteArrayToInt(byteArray: ByteArray): Int {
            return (byteArray[0].toInt() and 0xFF shl 24) or (byteArray[1].toInt() and 0xFF shl 16) or (byteArray[2].toInt() and 0xFF shl 8) or (byteArray[3].toInt() and 0xFF)
        }

        private val hexArray = "0123456789ABCDEF".toCharArray()
        fun byte2Hex(bytes: ByteArray): String {
            val hexChars = CharArray(bytes.size * 2)
            for (j in bytes.indices) {
                val v = (bytes[j] and 0xFF.toByte()).toInt()

                hexChars[j * 2] = hexArray[v ushr 4]
                hexChars[j * 2 + 1] = hexArray[v and 0x0F]
            }
            return String(hexChars)
        }
    }
}