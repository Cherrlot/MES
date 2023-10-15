package com.zhizhunbao.module.board.fragment

import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeremyliao.liveeventbus.LiveEventBus
import com.zhizhunbao.lib.common.base.BaseAppFragment
import com.zhizhunbao.lib.common.constant.ACTION_OPTION
import com.zhizhunbao.lib.common.constant.BUS_REFRESH_WORK_ORDER
import com.zhizhunbao.lib.common.ext.startTargetActivity
import com.zhizhunbao.lib.common.net.constant.State
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.activity.OptionListActivity
import com.zhizhunbao.module.board.adapter.WorkOrderListAdapter
import com.zhizhunbao.module.board.databinding.FragmentWorkOrderListBinding
import com.zhizhunbao.module.board.viewmodel.WorkOrderViewModel

/**
 * 工单列表
 */
class WorkOrderFragment :
    BaseAppFragment<WorkOrderViewModel, FragmentWorkOrderListBinding>() {

    private val mAdapter = WorkOrderListAdapter {
        // 操作详情
        requireContext().startTargetActivity<OptionListActivity>(
            bundleOf(
                ACTION_OPTION to it
            )
        )
    }

    override val layoutResId: Int
        get() = R.layout.fragment_work_order_list

    override fun initView() {
        initList()
    }

    override fun onResume() {
        super.onResume()
        if (firstLoad) {
            // 刷新数据
            mBinding.refreshLayout.autoRefresh()
        }
    }

    private fun initList() {
        mBinding.mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mBinding.mRecyclerView.adapter = mAdapter

        mBinding.refreshLayout.setOnRefreshListener {
            // 调用接口前先隐藏错误页
            viewModel.netRequestState.value = State(StateType.SUCCESS)
            //下拉刷新
            viewModel.mPage = 1
            viewModel.getDataList()
        }
        mBinding.refreshLayout.setOnLoadMoreListener {
            //上拉加载
            viewModel.mPage++
            viewModel.getDataList()
        }
        // 添加错误页
        addInfoView(mBinding.refreshLayout.parent as ViewGroup) {
            viewModel.netRequestState.value = State(StateType.SUCCESS)
            //下拉刷新
            viewModel.mPage = 1
            viewModel.getDataList()
        }
    }

    override fun initObserve() {
        // 获取预警列表
        viewModel.mOptionList.observe(this) {
            mBinding.refreshLayout.finishRefresh()
            mBinding.refreshLayout.finishLoadMore()
            if (viewModel.mPage == 1)
                mAdapter.setList(it)
            else
                mAdapter.addList(it)

            // 数据加载完成，停止加载更多
            if (viewModel.mTotalPage == viewModel.mPage)
                mBinding.refreshLayout.finishLoadMoreWithNoMoreData()
        }
        LiveEventBus.get(BUS_REFRESH_WORK_ORDER, Boolean::class.java).observe(this) {
            mBinding.refreshLayout.autoRefresh()
        }
    }

}