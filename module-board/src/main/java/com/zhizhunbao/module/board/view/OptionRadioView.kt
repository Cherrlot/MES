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

class OptionRadioView : OptionBaseView {
    private lateinit var mRoot: View
    private lateinit var mTitle: TextView
    private lateinit var mContent: RadioGroup
    private lateinit var mOptionItemBean: OptionItemBean

    constructor(context: Context, optionItemBean: OptionItemBean, groupPosition: Int? = null) : super(context) {
        mOptionItemBean = optionItemBean
        mRoot = LayoutInflater.from(context).inflate(R.layout.view_option_radio, this)
        mTitle = mRoot.findViewById(R.id.tvTitle)
        mContent = mRoot.findViewById(R.id.rg)

        val group = optionItemBean.groups
        if (!group.isNullOrEmpty()) {
            mTitle.visibility = GONE
            initGroupRadio(groupPosition.safe())
        } else {
            setTitle(optionItemBean.title)
            initRadio()
        }
    }

    private fun initGroupRadio(position: Int) {
        val p = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        val groups = mOptionItemBean.groups?.get(position)?.options
        groups?.forEach {
            val radioButton = RadioButton(context)
            radioButton.text = it
            mContent.addView(radioButton, p)
        }
        mContent.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = mRoot.findViewById<RadioButton>(checkedId)
            mOptionItemBean.radioValue = radioButton?.text.toString()
        }
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

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    fun setTitle(title: String?) {
        mTitle.text = title
    }

    override fun clear() {
        mContent.clearCheck()
    }
}