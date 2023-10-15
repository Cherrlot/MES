package com.zhizhunbao.module.board.adapter

import android.graphics.drawable.Drawable
import com.zhizhunbao.lib.common.base.BaseAppAdapter
import com.zhizhunbao.lib.common.base.BaseViewHolder
import com.zhizhunbao.lib.common.bean.OptionBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.tool.drawable
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.databinding.ItemWorkOrderBinding

/**
 * description:工单条目adapter
 */
class WorkOrderListAdapter(private var clickListener: (OptionBean) -> Unit) :
    BaseAppAdapter<ItemWorkOrderBinding, OptionBean>() {
    override val layoutResID: Int
        get() = R.layout.item_work_order

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = listDatas[holder.absoluteAdapterPosition]
        (holder.mBinding as ItemWorkOrderBinding).let { mBinding ->
            mBinding.bean = data
            mBinding.root.setOnClickListener {
                clickListener.invoke(data)
            }

            val drawable: Drawable? = when(data.Status) {
                "Created" -> com.zhizhunbao.lib.common.R.drawable.circle_red.drawable
                "Doing" -> com.zhizhunbao.lib.common.R.drawable.circle_green.drawable
                "Released" -> com.zhizhunbao.lib.common.R.drawable.circle_orange.drawable
                "Done" -> com.zhizhunbao.lib.common.R.drawable.circle_blue.drawable
                else -> com.zhizhunbao.lib.common.R.drawable.circle_red.drawable
            }
            mBinding.ivStatus.setImageDrawable(drawable)
            mBinding.pb.progress = if (data.Finish.safe() > data.Plan.safe() || data.Plan.safe() <= 0) 100 else data.Finish.safe() * 100 / data.Plan.safe()
        }
    }
}