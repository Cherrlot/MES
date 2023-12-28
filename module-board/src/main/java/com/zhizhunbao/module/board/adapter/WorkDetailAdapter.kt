package com.zhizhunbao.module.board.adapter

import com.zhizhunbao.lib.common.base.BaseAppAdapter
import com.zhizhunbao.lib.common.base.BaseViewHolder
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.databinding.ItemWorkMsgBinding

class WorkDetailAdapter:
    BaseAppAdapter<ItemWorkMsgBinding, String>() {
    override val layoutResID: Int
        get() = R.layout.item_work_msg

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = listDatas[holder.absoluteAdapterPosition]
        (holder.mBinding as ItemWorkMsgBinding).let { mBinding ->
            if (data.contains(":")) {
                val list = data.split(":")
                mBinding.tvName.text = list[0]
                mBinding.tvContent.text = list[1]
            } else {
                mBinding.tvName.text = data
            }
        }
    }
}