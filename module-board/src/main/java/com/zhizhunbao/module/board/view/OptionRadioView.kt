package com.zhizhunbao.module.board.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.zhizhunbao.lib.common.bean.OptionItemBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.module.board.R

class OptionRadioView : LinearLayout {
    private lateinit var mRoot: View
    private lateinit var mTitle: TextView
    private lateinit var mContent: RadioGroup
    private lateinit var mOptionItemBean: OptionItemBean

    constructor(context: Context?, optionItemBean: OptionItemBean) : super(context) {
        mOptionItemBean = optionItemBean
        mRoot = LayoutInflater.from(context).inflate(R.layout.view_option_radio, this)
        mTitle = mRoot.findViewById(R.id.tvTitle)
        mContent = mRoot.findViewById(R.id.rg)
        setTitle(optionItemBean.title)

        initRadio()
    }

    private fun initRadio() {
        mOptionItemBean.radioValue = mOptionItemBean.value.safe()

        val p = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        var id: Int? = null
        mOptionItemBean.options?.forEach {
            val radioButton = RadioButton(context)
            radioButton.text = it
            radioButton.isChecked = false
            mContent.addView(radioButton, p)
            if (it == mOptionItemBean.value)
                id = radioButton.id
        }
        if (id != null)
            mContent.check(id!!)
        mContent.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = mRoot.findViewById<RadioButton>(checkedId)
            mOptionItemBean.radioValue = radioButton.text.toString()
        }
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