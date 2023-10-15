package com.zhizhunbao.lib.common.util

import android.text.TextUtils
import android.widget.Toast
import com.zhizhunbao.lib.common.CommonApplication


object ToastUtil {
    fun showToast(message: String?) {
        try {
            if (!TextUtils.isEmpty(message)) Toast.makeText(CommonApplication.instance, message, Toast.LENGTH_SHORT)
                .show()
        } catch (e: Exception) {
            UIThreadHelper.runOnUiThread {
                if (!TextUtils.isEmpty(message)) Toast.makeText(CommonApplication.instance, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}