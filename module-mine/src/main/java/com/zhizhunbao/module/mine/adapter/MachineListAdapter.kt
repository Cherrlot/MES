package com.zhizhunbao.module.mine.adapter

import android.graphics.Color
import com.zhizhunbao.lib.common.base.BaseAppAdapter
import com.zhizhunbao.lib.common.base.BaseViewHolder
import com.zhizhunbao.lib.common.bean.MachineBean
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.tool.color
import com.zhizhunbao.module.mine.R
import com.zhizhunbao.module.mine.databinding.ItemDeviceNoBinding

/**
 * description:设备条目adapter
 */
class MachineListAdapter(private var clickListener: (MachineBean, Int) -> Unit) :
    BaseAppAdapter<ItemDeviceNoBinding, MachineBean>() {
    /** 上一次选中的设备**/
    private var mLastSelectPosition = -1

    override val layoutResID: Int
        get() = R.layout.item_device_no

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = listDatas[holder.absoluteAdapterPosition]
        (holder.mBinding as ItemDeviceNoBinding).let { mBinding ->
            mBinding.bean = data
            if (AppLocalData.machineNo == data.No) {
                mLastSelectPosition = holder.absoluteAdapterPosition
                mBinding.mClDeviceNo.setBackgroundResource(com.zhizhunbao.lib.common.R.drawable.shape_blue_r8_bg)
                mBinding.tvTitle.setTextColor(Color.WHITE)
            } else {
                mBinding.mClDeviceNo.setBackgroundResource(com.zhizhunbao.lib.common.R.drawable.shape_white_r8_bg)
                mBinding.tvTitle.setTextColor(com.zhizhunbao.lib.common.R.color.color333333.color)
            }
            mBinding.root.setOnClickListener {
                clickListener.invoke(data, holder.absoluteAdapterPosition)
            }
        }
    }

    fun changeSelect(selectPosition: Int) {
        notifyItemChanged(mLastSelectPosition)
        notifyItemChanged(selectPosition)
    }
}