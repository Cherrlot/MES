package com.zhizhunbao.module.board.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import com.google.gson.Gson
import com.zhizhunbao.lib.common.bean.OptionItemBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.module.board.R

class OptionCheckView : OptionBaseView {
    private lateinit var mRoot: View
    private lateinit var mTitle: TextView
    private lateinit var mContent: LinearLayout
    private lateinit var mOptionItemBean: OptionItemBean
    private val mCheckList: MutableList<String> = mutableListOf()

    constructor(context: Context, optionItemBean: OptionItemBean, groupPosition: Int? = null) : super(context) {
        mOptionItemBean = optionItemBean
        mRoot = LayoutInflater.from(context).inflate(R.layout.view_option_check, this)
        mTitle = mRoot.findViewById(R.id.tvTitle)
        mContent = mRoot.findViewById(R.id.llCheck)

        val group = optionItemBean.groups
        if (!group.isNullOrEmpty()) {
            mTitle.visibility = GONE
            initGroupCheckbox(groupPosition.safe())
        } else {
            setTitle(optionItemBean.title)
            try {
                val gson = Gson()
                val list = gson.fromJson(mOptionItemBean.value, List::class.java)
                list.forEach {
                    mCheckList.add(it.toString())
                }
            } catch (_: Exception) {}
            mOptionItemBean.checkValue = mCheckList

            initCheckbox()
        }
    }

    private fun initGroupCheckbox(position: Int) {
        val groups = mOptionItemBean.groups?.get(position)?.options
        val p = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        groups?.forEach {
            val checkBox = CheckBox(context)
            checkBox.text = it
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (mOptionItemBean.checkValue.isNullOrEmpty())
                    mOptionItemBean.checkValue = mutableListOf()
                if (isChecked) {
                    if (!mOptionItemBean.checkValue?.contains(it).safe()) {
                        mOptionItemBean.checkValue?.add(it)
                    }
                } else {
                    mOptionItemBean.checkValue?.remove(it)
                }
            }
            mContent.addView(checkBox, p)
        }
    }

    private fun initCheckbox() {
        val p = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        mOptionItemBean.options?.forEach {
            val checkBox = CheckBox(context)
            checkBox.text = it
            checkBox.isChecked = mCheckList.contains(it)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (mOptionItemBean.checkValue.isNullOrEmpty())
                    mOptionItemBean.checkValue = mutableListOf()
                if (isChecked) {
                    if (!mOptionItemBean.checkValue?.contains(it).safe()) {
                        mOptionItemBean.checkValue?.add(it)
                    }
                } else {
                    mOptionItemBean.checkValue?.remove(it)
                }
            }
            mContent.addView(checkBox, p)
        }
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    fun setTitle(title: String?) {
        mTitle.text = title
    }

    override fun clear() {
        mContent.children.forEach {
            val checkBox = it as CheckBox
            checkBox.isChecked = false
        }
    }
}