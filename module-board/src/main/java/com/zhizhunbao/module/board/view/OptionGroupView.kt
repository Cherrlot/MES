package com.zhizhunbao.module.board.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.isVisible
import com.zhizhunbao.lib.common.bean.OptionItemBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.module.board.R

class OptionGroupView : OptionBaseView {
    private lateinit var mRoot: View
    private lateinit var mTitle: TextView
    private lateinit var mRadioGroup: RadioGroup
    private lateinit var mContent: LinearLayout
    private lateinit var mOptionItemBean: OptionItemBean
//    private var mSelectIndex = 0
    private val mOptionItemMap = HashMap<Int, MutableList<OptionBaseView>>()

    constructor(context: Context, optionItemBean: OptionItemBean) : super(context) {
        mOptionItemBean = optionItemBean
        mRoot = LayoutInflater.from(context).inflate(R.layout.view_option_group, this)
        mTitle = mRoot.findViewById(R.id.tvTitle)
        mRadioGroup = mRoot.findViewById(R.id.rg)
        mContent = mRoot.findViewById(R.id.llGroup)
        setTitle(optionItemBean.title)

        initRadio()
        initChildView()
    }

    private fun initChildView() {
        mOptionItemBean.groups?.forEachIndexed { index, _ ->
            val list :MutableList<OptionBaseView> = mutableListOf()
            val childLayout = LinearLayout(context)
            when (mOptionItemBean.type) {
                "input" -> addInputView(childLayout, list)
                "radio" -> addRadioView(childLayout, index, list)
                "checkbox" -> addCheckboxView(childLayout, index, list)
            }
            childLayout.isVisible = index == 0
            mContent.addView(childLayout)
            mOptionItemMap[index] = list
        }
    }

    private fun addRadioView(viewGroup: ViewGroup, groupPosition: Int, list :MutableList<OptionBaseView>) {
        val p = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        val inputView = OptionRadioView(context, mOptionItemBean, groupPosition)
        viewGroup.addView(inputView, viewGroup.childCount.safe(), p)
        list.add(inputView)
    }

    private fun addCheckboxView(viewGroup: ViewGroup, groupPosition: Int, list :MutableList<OptionBaseView>) {
        val p = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        val inputView = OptionCheckView(context, mOptionItemBean, groupPosition)
        viewGroup.addView(inputView, viewGroup.childCount.safe(), p)
        list.add(inputView)
    }

    private fun addInputView(viewGroup: ViewGroup, list :MutableList<OptionBaseView>) {
        val p = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        val inputView = OptionInputView(context, mOptionItemBean)
        viewGroup.addView(inputView, viewGroup.childCount.safe(), p)
        list.add(inputView)
    }

    private fun initRadio() {
        val p = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        var defaultId = 0
        mOptionItemBean.groups?.forEachIndexed { index, optionGroupItemBean ->
            val radioButton = RadioButton(context)
            radioButton.text = optionGroupItemBean.group
            mRadioGroup.addView(radioButton, p)
            if (index == 0) {
                defaultId = radioButton.id
            }
        }
        mRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = mRoot.findViewById<RadioButton>(checkedId)
            radioButton?.let {
                val selectIndex = mRadioGroup.indexOfChild(radioButton)
                mContent.children.forEachIndexed { index, view ->  view.isVisible = index == selectIndex }
                mOptionItemBean.groupValue = radioButton.text.toString()
            }
            clear()
        }
        mRadioGroup.check(defaultId)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    fun setTitle(title: String?) {
        mTitle.text = title
    }

    override fun clear() {
        mContent.children.forEachIndexed { index, _ ->
            mOptionItemMap[index]?.forEach {
                it.clear()
            }
            mOptionItemBean.inputValue = null
            mOptionItemBean.radioValue = null
            mOptionItemBean.checkValue = null
        }
    }
}