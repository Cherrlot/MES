package com.zhizhunbao.lib.common.util

import android.graphics.*
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import kotlin.math.ceil
import kotlin.math.roundToInt


/**
 * Created by xwh on 2020/3/18  星期三
 * description:Bitmap工具类
 */
class BitmapUtils {

    companion object {

        /**
         * bitmap旋转
         */
        fun rotateBitmap(bitmap: Bitmap, rotate: Float): Bitmap {
            val width = bitmap.width
            val height = bitmap.height
            val matrix = Matrix()
            matrix.setRotate(rotate)
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false)
        }

        /**
         * file图片旋转
         */
        fun rotateFile(file: File, rotate: Float): File {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            val newBitmap = rotateBitmap(bitmap, rotate)
            val newFile = File(file.absolutePath)
            var outputStream: OutputStream? = null
            try {
                outputStream = FileOutputStream(newFile)
                newBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                outputStream?.flush()
                outputStream?.close()
            }
            return newFile
        }

        fun yuv2Bitmap(data: ByteArray, width: Int, height: Int): Bitmap {

            val frameSize = width * height
            val rgba = IntArray(frameSize)
            for (i in 0 until height) {
                for (j in 0 until width) {
                    var y = 0xff and data[i * width + j].toInt()
                    val u =
                        0xff and data[frameSize + (i shr 1) * width + (j and 1.inv()) + 0].toInt()
                    val v =
                        0xff and data[frameSize + (i shr 1) * width + (j and 1.inv()) + 1].toInt()
                    y = if (y < 16) 16 else y
                    var r = (1.164f * (y - 16) + 1.596f * (v - 128)).roundToInt()
                    var g =
                        (1.164f * (y - 16) - 0.813f * (v - 128) - 0.391f * (u - 128)).roundToInt()
                    var b = (1.164f * (y - 16) + 2.018f * (u - 128)).roundToInt()
                    r = if (r < 0) 0 else if (r > 255) 255 else r
                    g = if (g < 0) 0 else if (g > 255) 255 else g
                    b = if (b < 0) 0 else if (b > 255) 255 else b
                    rgba[i * width + j] = -0x1000000 + (b shl 16) + (g shl 8) + r
                }
            }
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            bmp.setPixels(rgba, 0, width, 0, 0, width, height)
            return bmp
        }

        fun convert(bitmap: Bitmap, width: Int, height: Int): Bitmap {
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmp)
            val matrix = Matrix()
//            matrix.postScale(1f,-1f)
            matrix.postScale(-1f, 1f)
            val bmp1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            canvas.drawBitmap(
                bmp1,
                Rect(0, 0, bmp1.width, bmp1.height),
                Rect(0, 0, bitmap.width, bitmap.height),
                null
            )
            return bmp
        }


        fun compress(bitmap: Bitmap, quality: Int = 100): ByteArrayOutputStream {
            val options = BitmapFactory.Options()
            options.inSampleSize = computeSize(bitmap.width, bitmap.height)
            var baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val array = baos.toByteArray()
            val tagBitmap = BitmapFactory.decodeByteArray(array, 0, array.size, options)
            baos = ByteArrayOutputStream()
            tagBitmap?.compress(Bitmap.CompressFormat.JPEG, quality, baos)
            tagBitmap?.recycle()
            return baos
        }

        private fun computeSize(width: Int, height: Int): Int {
            val srcWidth = if (width % 2 == 1) width + 1 else width
            val srcHeight = if (height % 2 == 1) height + 1 else height
            val longSide: Int = srcWidth.coerceAtLeast(srcHeight)
            val shortSide: Int = srcWidth.coerceAtMost(srcHeight)
            val scale = shortSide.toFloat() / longSide
            return if (scale <= 1 && scale > 0.5625) {
                if (longSide < 1664) {
                    1
                } else if (longSide < 4990) {
                    2
                } else if (longSide in 4991..10239) {
                    4
                } else {
                    if (longSide / 1280 == 0) 1 else longSide / 1280
                }
            } else if (scale <= 0.5625 && scale > 0.5) {
                if (longSide / 1280 == 0) 1 else longSide / 1280
            } else {
                ceil(longSide / (1280.0 / scale)).toInt()
            }
        }

        /**
         * 获取正确缩放裁剪适应IamgeView的Bitmap
         * @param imageView
         * @param bitmap
         * @return
         */
        fun createFitBitmap(imageView: ImageView, bitmap: Bitmap): Bitmap? {
            val widthTarget: Int = imageView.getWidth()
            val heightTarget: Int = imageView.getHeight()
            val widthBitmap = bitmap.width
            val heightBitmap = bitmap.height
            val result = if (widthBitmap >= widthTarget && heightBitmap >= heightTarget) {
                createLargeToSmallBitmap(
                    widthBitmap,
                    heightBitmap,
                    widthTarget,
                    heightTarget,
                    bitmap
                )
            } else if (widthBitmap >= widthTarget && heightBitmap < heightTarget) {
                val temp = createLargeWidthToEqualHeightBitmap(
                    widthBitmap,
                    heightBitmap,
                    widthTarget,
                    heightTarget,
                    bitmap
                )
                createLargeToSmallBitmap(temp.width, temp.height, widthTarget, heightTarget, temp)
            } else if (widthBitmap < widthTarget && heightBitmap >= heightTarget) {
                val temp = createLargeHeightToEqualWidthBitmap(
                    widthBitmap,
                    heightBitmap,
                    widthTarget,
                    heightTarget,
                    bitmap
                )
                createLargeToSmallBitmap(temp.width, temp.height, widthTarget, heightTarget, temp)
            } else {
                val temp = createSmallToEqualBitmap(
                    widthBitmap,
                    heightBitmap,
                    widthTarget,
                    heightTarget,
                    bitmap
                )
                createFitBitmap(imageView, temp)
            }
            return result
        }


        private fun createLargeToSmallBitmap(
            widthBitmap: Int,
            heightBitmap: Int,
            widthTarget: Int,
            heightTarget: Int,
            bitmap: Bitmap
        ): Bitmap? {
            val x = (widthBitmap - widthTarget) / 2
            val y = (heightBitmap - heightTarget) / 2
            return Bitmap.createBitmap(bitmap, x, y, widthTarget, heightTarget)
        }

        private fun createLargeWidthToEqualHeightBitmap(
            widthBitmap: Int,
            heightBitmap: Int,
            widthTarget: Int,
            heightTarget: Int,
            bitmap: Bitmap
        ): Bitmap {
            val scale = heightTarget * 1.0 / heightBitmap
            return Bitmap.createScaledBitmap(
                bitmap,
                (widthBitmap * scale).toInt(),
                heightTarget,
                false
            )
        }

        private fun createLargeHeightToEqualWidthBitmap(
            widthBitmap: Int,
            heightBitmap: Int,
            widthTarget: Int,
            heightTarget: Int,
            bitmap: Bitmap
        ): Bitmap {
            val scale = widthTarget * 1.0 / widthBitmap
            return Bitmap.createScaledBitmap(
                bitmap,
                widthTarget,
                (heightTarget * scale).toInt(),
                false
            )
        }

        private fun createSmallToEqualBitmap(
            widthBitmap: Int,
            heightBitmap: Int,
            widthTarget: Int,
            heightTarget: Int,
            bitmap: Bitmap
        ): Bitmap {
            val scaleWidth = widthTarget * 1.0 / widthBitmap
            val scaleHeight = heightTarget * 1.0 / heightBitmap
            val scale = Math.min(scaleWidth, scaleHeight)
            return Bitmap.createScaledBitmap(
                bitmap,
                (widthBitmap * scale).toInt(), (heightBitmap * scale).toInt(), false
            )
        }

    }
}