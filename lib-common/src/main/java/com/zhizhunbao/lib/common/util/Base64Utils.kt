package com.zhizhunbao.lib.common.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.zhizhunbao.lib.common.ext.safe
import java.io.*
import java.math.BigInteger


/**
 * Created by xwh on 2020/3/16  星期一
 * description:
 */
class Base64Utils {

    companion object {

        fun getCardNum(cardNumber: String): String {
            if (cardNumber.length < 8) {
                return cardNumber
            }
            var temp = cardNumber.substring(0, 8)
            val one = temp.substring(6, 8)
            val two = temp.substring(4, 6)
            val three = temp.substring(2, 4)
            val four = temp.substring(0, 2)
            temp = "$one$two$three$four"
            val code = BigInteger(temp, 16)
            return code.toString()
        }

        /**
         * bitmap转base64
         */
        fun bitmapToBase64(bitmap: Bitmap): String? {
            var result: String? = null
            var baos: ByteArrayOutputStream? = null
            try {
                baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                baos.flush()
                baos.close()
                val bitmapBytes = baos.toByteArray()
                result = Base64.encodeToString(bitmapBytes, Base64.NO_WRAP)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                baos?.flush()
                baos?.close()
            }
            return result
        }

        /**
         * outputStream转base64
         */
        fun outputStreamToBase64(baos: ByteArrayOutputStream): String? {
            var result: String? = null
            try {
                val bitmapBytes = baos.toByteArray()
                result = Base64.encodeToString(bitmapBytes, Base64.NO_WRAP)
                baos.flush()
                baos.close()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                baos.flush()
                baos.close()
            }
            return result
        }

        fun bytToBase64(bitmapBytes: ByteArray?, width: Int, height: Int): String? {
            var result: String? = null
            try {
                val bitmap = BitmapUtils.yuv2Bitmap(bitmapBytes!!, width, height)
                val baos = BitmapUtils.compress(bitmap, 60)
                val bytes = baos.toByteArray()
                result = Base64.encodeToString(bytes, Base64.NO_WRAP)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
            }
            return result
        }

        fun fileToBase64(file: File): String? {
            var result: String? = null
            var input: FileInputStream? = null
            try {
                input = FileInputStream(file)
                val fileBytes = input.readBytes()
                result = Base64.encodeToString(fileBytes, Base64.NO_WRAP)
                input.close()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                input?.close()
            }
            return result
        }

        fun stringToBitmap(content: String): Bitmap? {
            var bitmap: Bitmap? = null
            try {
                val bitmapArray: ByteArray = Base64.decode(content, Base64.NO_WRAP)
                bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return bitmap
        }

        /**
         * 把字节数组保存为一个文件
         */
        fun getFileFromBytes(b: ByteArray?, outputFile: String?): File? {
            var file: File? = null
            try {
                file = File(outputFile)
                val fos = FileOutputStream(file)
                fos.write(b, 0, b?.size.safe())
                fos.flush()
                fos.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return file
        }

        fun saveBitmapFile(bitmap: Bitmap, outputFile: String) {
            val file: File?
            try {
                file = File(outputFile)
                val fos = FileOutputStream(file)
                val bos = BufferedOutputStream(fos)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                bos.flush()
                bos.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

    }
}