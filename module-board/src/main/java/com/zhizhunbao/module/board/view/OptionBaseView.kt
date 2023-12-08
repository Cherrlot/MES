package com.zhizhunbao.module.board.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

abstract class OptionBaseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    abstract fun clear()
}