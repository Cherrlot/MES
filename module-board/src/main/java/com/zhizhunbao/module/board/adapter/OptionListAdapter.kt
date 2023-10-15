package com.zhizhunbao.module.board.adapter

import com.zhizhunbao.lib.common.base.BaseAppAdapter
import com.zhizhunbao.lib.common.base.BaseViewHolder
import com.zhizhunbao.lib.common.bean.OptionListBean
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.databinding.ItemOptionBinding

/**
 * description:操作条目adapter
 */
class OptionListAdapter(private var clickListener: (OptionListBean) -> Unit) :
    BaseAppAdapter<ItemOptionBinding, OptionListBean>() {
    override val layoutResID: Int
        get() = R.layout.item_option

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = listDatas[holder.absoluteAdapterPosition]
        (holder.mBinding as ItemOptionBinding).let { mBinding ->
            mBinding.bean = data
            mBinding.root.setOnClickListener {
                clickListener.invoke(data)
            }
        }
    }
}