package com.zhizhunbao.lib.common.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.zhizhunbao.lib.common.base.BaseViewHolder

abstract class BaseAppAdapter<DB : ViewDataBinding, T>(
    var listDatas: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<BaseViewHolder>() {
    /** 布局 id */
    abstract val layoutResID: Int
    protected lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        context = parent.context
        val binding = DataBindingUtil.inflate<DB>(
            LayoutInflater.from(parent.context),
            layoutResID, parent, false
        )
        return BaseViewHolder(binding, binding.root)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        convert(holder, position)
    }

    abstract fun convert(holder: BaseViewHolder, position: Int)

    override fun getItemCount(): Int {
        return listDatas.size
    }

    fun setList(list: Collection<T>?) {
        if (list.isNullOrEmpty()) {
            listDatas.apply {
                val previousSize = size
                clear()
                notifyItemRangeRemoved(0, previousSize)
            }
        } else {
            listDatas.apply {
                val previousSize = size
                clear()
                notifyItemRangeRemoved(0, previousSize)
                addAll(list)
                notifyItemRangeChanged(0, list.size, 0)
            }
        }
    }

    fun addList(list: Collection<T>?) {
        list?.let {
            listDatas.apply {
                addAll(it)
                notifyItemRangeChanged(it.size, list.size)
            }
        }
    }

    fun removeItem(position: Int = listDatas.size - 1) {
        listDatas.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): T {
        return listDatas[position]
    }

    fun updateItem(position: Int, data: T) {
        listDatas.removeAt(position)
        notifyItemRemoved(position)
        listDatas.add(position, data)
        notifyItemInserted(position)
    }
}