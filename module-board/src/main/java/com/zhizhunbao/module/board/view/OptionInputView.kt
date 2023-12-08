package com.zhizhunbao.module.board.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.zhizhunbao.lib.common.bean.OptionItemBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.module.board.R

class OptionInputView : OptionBaseView {
    private lateinit var mRoot: View
    private lateinit var mTitle: TextView
    private lateinit var mContent: AppCompatEditText
    private lateinit var mOptionItemBean: OptionItemBean

    constructor(context: Context, optionItemBean: OptionItemBean) : super(context) {
        mOptionItemBean = optionItemBean
        mRoot = LayoutInflater.from(context).inflate(R.layout.view_option_input, this)
        mTitle = mRoot.findViewById(R.id.tvTitle)
        mContent = mRoot.findViewById(R.id.etContent)

        val group = optionItemBean.groups
        if (!group.isNullOrEmpty()) {
            mTitle.visibility = GONE
            mContent.addTextChangedListener {
                mOptionItemBean.inputValue = it?.toString()
            }
        } else {
            mContent.setText(mOptionItemBean.value)
            mOptionItemBean.inputValue = mOptionItemBean.value.safe()
            mContent.addTextChangedListener {
                mOptionItemBean.inputValue = it?.toString()
            }
            setTitle(optionItemBean.title)
        }
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    fun setTitle(title: String?) {
        mTitle.text = title
    }

    fun setContent(content: String?) {
        mContent.setText(content)
    }

    fun getContent() : String {
        return mContent.text?.toString().safe()
    }

    override fun clear() {
        mContent.setText("")
    }
}