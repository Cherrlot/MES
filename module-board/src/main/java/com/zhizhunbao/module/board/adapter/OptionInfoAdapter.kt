package com.zhizhunbao.module.board.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import com.zhizhunbao.lib.common.base.BaseAppAdapter
import com.zhizhunbao.lib.common.base.BaseViewHolder
import com.zhizhunbao.lib.common.bean.OptionInfoBean
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.databinding.ItemOptionInfoBinding

/**
 * description:操作条目adapter
 */
class OptionInfoAdapter:
    BaseAppAdapter<ItemOptionInfoBinding, OptionInfoBean>() {
    override val layoutResID: Int
        get() = R.layout.item_option_info

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = listDatas[holder.absoluteAdapterPosition]
        (holder.mBinding as ItemOptionInfoBinding).let { mBinding ->
            mBinding.bean = data

            mBinding.mItemRecyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = OptionInfoChildAdapter()
            mBinding.mItemRecyclerView.adapter = adapter
            adapter.setList(data.items)
        }
    }
}