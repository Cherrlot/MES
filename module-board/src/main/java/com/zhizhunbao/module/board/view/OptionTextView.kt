package com.zhizhunbao.module.board.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.zhizhunbao.lib.common.bean.OptionItemBean
import com.zhizhunbao.module.board.R

class OptionTextView : LinearLayout {
    private lateinit var mRoot: View
    private lateinit var mTitle: TextView
    private lateinit var mOptionItemBean: OptionItemBean

    constructor(context: Context?, optionItemBean: OptionItemBean) : super(context) {
        mOptionItemBean = optionItemBean
        mRoot = LayoutInflater.from(context).inflate(R.layout.view_option_text, this)
        mTitle = mRoot.findViewById(R.id.tvTitle)
        setTitle(optionItemBean.title)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setTitle(title: String?) {
        mTitle.text = title
    }
}