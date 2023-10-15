package com.zhizhunbao.lib.common.bind.adapter

import android.os.SystemClock
import android.view.View
import androidx.databinding.BindingAdapter
import com.zhizhunbao.lib.common.ext.fitsStatusBar
import com.zhizhunbao.lib.common.ext.safe

/**
 * 添加clickFilter标签，添加多次点击过滤
 */
@BindingAdapter("android:clickFilter")
fun clickFilter(view: View, clickListener: View.OnClickListener?) {
    val mHits = LongArray(2)
    view.setOnClickListener { v ->
        System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
        mHits[mHits.size - 1] = SystemClock.uptimeMillis()
        if (mHits[0] < SystemClock.uptimeMillis() - 500) {
            clickListener?.onClick(v)
        }
    }
}

/**
 * 设置 [v] 是否适应状态栏
 * > [v] 为布局中最上面的 [View] 且布局延伸到状态栏下方时，添加状态栏高度，并添加状态栏高度的 paddingTop，
 * 以适应沉浸式体验
 */
@BindingAdapter("android:bind_fits_status_bar")
fun fitsStatusBar(v: View, fits: Boolean?) {
    if (!fits.safe()) {
        return
    }
    v.fitsStatusBar()
}