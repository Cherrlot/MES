package com.zhizhunbao.module.board.adapter

import com.zhizhunbao.lib.common.base.BaseAppAdapter
import com.zhizhunbao.lib.common.base.BaseViewHolder
import com.zhizhunbao.lib.common.bean.OptionInfoItemBean
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.databinding.ItemOptionInfoChildBinding

class OptionInfoChildAdapter :
    BaseAppAdapter<ItemOptionInfoChildBinding, OptionInfoItemBean>() {
    override val layoutResID: Int
        get() = R.layout.item_option_info_child

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = listDatas[holder.absoluteAdapterPosition]
        (holder.mBinding as ItemOptionInfoChildBinding).bean = data
    }
}