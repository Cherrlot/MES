package com.zhizhunbao.module.board.adapter

import com.zhizhunbao.lib.common.base.BaseAppAdapter
import com.zhizhunbao.lib.common.base.BaseViewHolder
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.databinding.ItemMsgGridBinding

class WorkMessageAdapter:
    BaseAppAdapter<ItemMsgGridBinding, String>() {
    override val layoutResID: Int
        get() = R.layout.item_msg_grid

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = listDatas[holder.absoluteAdapterPosition]
        (holder.mBinding as ItemMsgGridBinding).let { mBinding ->
            mBinding.tvTitle.text = data
        }
    }
}