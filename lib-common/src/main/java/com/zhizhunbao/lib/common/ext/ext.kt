package com.zhizhunbao.lib.common.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.luck.picture.lib.basic.PictureSelectionCameraModel
import com.luck.picture.lib.config.PictureSelectionConfig
import com.zhizhunbao.lib.common.util.ToastUtil

fun String?.safe(default: String = ""): String = this ?: default
fun Int?.safe(default: Int = 0) = this ?: default
fun Long?.safe(default: Long = 0) = this ?: default
fun Boolean?.safe(default: Boolean = false) = this ?: default
fun Float?.safe(default: Float = 0f) = this ?: default
fun Double?.safe(default: Double = 0.0) = this ?: default

fun String?.toast() = ToastUtil.showToast(this.safe())


/**
 * 获取格式化 保留[decimalLength] 位数的小数点，[intOptimization]true 代表整数不显示小数点
 */
fun Number.formatDecimal(decimalLength: Int = 1, intOptimization: Boolean = true): String {
    if (intOptimization && this.toFloat() - this.toInt() == 0f)
        return this.toInt().toString()
    return String.format("%.${decimalLength}f", this.toDouble())
}

/**
 * 获取格式化 保留[decimalLength] 位数的小数点，[intOptimization]true 代表整数不显示小数点
 */
fun String?.formatDecimal(decimalLength: Int = 1, intOptimization: Boolean = true): String {
    try {
        if (intOptimization && this.safe("0").toFloat() - this.safe("0").toInt() == 0f)
            return this.safe("0").toInt().toString()
        return String.format("%.${decimalLength}f", this.safe("0").toDouble())
    } catch (e: Exception) {
        return "0.00"
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString().trim())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

fun PictureSelectionCameraModel.setRequestedOrientation(requestedOrientation: Int): PictureSelectionCameraModel {
    PictureSelectionConfig.getInstance().requestedOrientation = requestedOrientation
    return this
}