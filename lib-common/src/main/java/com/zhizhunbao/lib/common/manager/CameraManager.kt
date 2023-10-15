package com.zhizhunbao.lib.common.manager

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.Size
import android.view.Surface
import androidx.camera.core.*
import androidx.camera.core.Camera
import androidx.camera.core.impl.utils.executor.CameraXExecutors
import androidx.camera.core.impl.utils.futures.FutureCallback
import androidx.camera.core.impl.utils.futures.Futures
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.R
import com.zhizhunbao.lib.common.constant.PORT_ORIENTATION
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.util.ImageFormatUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.ExecutorService


/**
 * Created by xwh on 2020/3/3  星期二
 * description:系统相机管理类
 */
class CameraManager {

    companion object {

        private var lensFacing = CameraSelector.LENS_FACING_BACK

        const val TAG = "SETTLEMENT"
        const val PHOTO_EXTENSION = ".png"

        // 该参数目前没有起任何作用
        private var currentAnalyzeTime = 0L

        private var mImageCapture: ImageCapture? = null
        private var mCamera: Camera? = null
        private var isFocus = false
        private var focusStart = false
        private var isFocusSuccess = false

        /**
         * 创建应用存储图片目录
         */
        fun getOutputDirectory(context: Context): File {
            return File(context.filesDir, context.resources.getString(R.string.app_retention)).apply { mkdirs() }
        }

        /**
         * 删除应用存储图片目录
         */
        fun deleteDirectory(context: Context) {
            val dirFile = File(context.filesDir, context.resources.getString(R.string.app_retention))
            //如果dir对应的文件不存在，或者不是一个目录
            if (!dirFile.exists() || !dirFile.isDirectory) {
                return
            }
            dirFile.listFiles().forEach {
                if (it.isFile) {
                    it.delete()
                }
            }
        }

        /**
         * 删除应用缓存图片
         */
        fun deleteCacheDir(context: Context) {
            val cacheDir = context.cacheDir
            if (cacheDir != null && cacheDir.exists() && cacheDir.isDirectory) {
                cacheDir.listFiles().forEach { file ->
                    if (file.isDirectory) {
                        file.listFiles().forEach { it.delete() }
                    } else {
                        file.delete()
                    }
                }

            }
        }


        /**
         * 创建文件
         */
        fun createFile(baseFolder: File, filename: String, extension: String) = File(baseFolder, filename + extension)

        /**
         * 初始化相机
         */

        @SuppressLint("UnsafeExperimentalUsageError", "RestrictedApi", "UnsafeOptInUsageError")
        fun initCamera(
            context: Context, view: PreviewView, lifecycleOwner: LifecycleOwner,
            executorService: ExecutorService, listener: ((Bitmap?, Boolean) -> Unit)? = null
        ): ImageCapture? {
            currentAnalyzeTime = 0L
            isFocusSuccess = false
            isFocus = false
            focusStart = false

            //选择摄像头
            val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({

                val cameraProvider = cameraProviderFuture.get()

                //初始化相机预览
                val preview = Preview.Builder()
                    .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                    .setTargetRotation(Surface.ROTATION_180)
                    .build()

                //初始化捕获照片
                mImageCapture = ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                    .setTargetRotation(Surface.ROTATION_180)
                    .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
                    .setMaxResolution(Size(2048, 1536))
                    .build()

                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                    .setTargetRotation(Surface.ROTATION_180)
                    .setDefaultResolution(Size(1440, 1080))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build().also {
                        it.setAnalyzer(executorService, ImageAnalysis.Analyzer { image ->
                            if (listener == null) {
                                image.close()
                                return@Analyzer
                            }
                            synchronized(this) {
                                val currentTime = System.currentTimeMillis()
                                if (currentTime - currentAnalyzeTime >= 100) {
                                    if (isFocus && !focusStart) {
                                        isFocus = false
                                        focusStart = true
                                        mCamera?.cameraControl?.let { cameraControl ->
                                            isFocus = true
                                            if (view != null) {
                                                val pointFactory = view.meteringPointFactory
                                                val afPointWidth = 1.0f / 6.0f // 1/6 total area
                                                val afPoint =
                                                    pointFactory.createPoint(view.width / 2f, view.height / 2f, afPointWidth)

                                                val future = cameraControl.startFocusAndMetering(
                                                    FocusMeteringAction.Builder(afPoint, FocusMeteringAction.FLAG_AF).build()
                                                )
                                                Futures.addCallback(future, object :
                                                    FutureCallback<FocusMeteringResult?> {
                                                    override fun onSuccess(result: FocusMeteringResult?) {
                                                        AppLog.e("聚焦成功")
                                                        focusStart = false
                                                        isFocusSuccess = true
                                                    }

                                                    override fun onFailure(t: Throwable) {
                                                        AppLog.e("聚焦失败")
                                                        focusStart = false
                                                        mCamera?.cameraControl?.cancelFocusAndMetering()
                                                    }
                                                }, CameraXExecutors.directExecutor())
                                            } else {
                                                focusStart = false
                                                isFocusSuccess = true
                                            }

                                        }
                                    }
                                    try {
                                        val bytes = ImageFormatUtils.getDataFromImage(image.image)
                                        val yuvImage =
                                            YuvImage(bytes, ImageFormat.NV21, image.width, image.height, null)
                                        val outInput = ByteArrayOutputStream()
                                        yuvImage.compressToJpeg(Rect(0, 0, image.width, image.height), 100, outInput)
                                        val newBytes = outInput.toByteArray()
                                        val bitmap = BitmapFactory.decodeByteArray(newBytes, 0, newBytes.size)

                                        listener.invoke(rotateBitmap(bitmap, 90f), isFocusSuccess)

                                        isFocusSuccess = false
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                        image.close()
                                    }

                                }
                                image.close()
                            }
                        })
                    }

                cameraProvider.unbindAll()
                //绑定 UseCase 到相机
                try {
                    mCamera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, mImageCapture, imageAnalysis)
                    mCamera?.cameraControl?.enableTorch(AppLocalData.enableTorch)
                    mCamera?.cameraControl?.setLinearZoom(0f)

                    //开始预览
                    preview.setSurfaceProvider(view.surfaceProvider)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(context))
            return mImageCapture
        }

        fun startFocus() {
            isFocus = true
        }

        fun stopFocus() {
            isFocus = false
            focusStart = false
            mCamera?.cameraControl?.cancelFocusAndMetering()
        }

        @SuppressLint("RestrictedApi")
        fun closeCamera(context: Context) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll()
        }

        private fun rotateBitmap(origin: Bitmap?, alpha: Float): Bitmap? {
            if (origin == null) {
                return null
            }
            val width = origin.width
            val height = origin.height
            val matrix = Matrix()
            matrix.setRotate(alpha)
            // 围绕原地进行旋转
            val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
            if (newBM == origin) {
                return newBM
            }
            origin.recycle()
            return newBM
        }
    }
}