package com.zhizhunbao.module.board.activity

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.jeremyliao.liveeventbus.LiveEventBus
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.bean.OptionBean
import com.zhizhunbao.lib.common.constant.ACTION_OPTION
import com.zhizhunbao.lib.common.constant.ACTION_OPTION_LIST
import com.zhizhunbao.lib.common.constant.BUS_REFRESH_OPTION
import com.zhizhunbao.lib.common.ext.startTargetActivity
import com.zhizhunbao.lib.common.net.constant.State
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.router.ROUTER_PATH_GPSDK
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.adapter.OptionListAdapter
import com.zhizhunbao.module.board.databinding.ActivityOptionListBinding
import com.zhizhunbao.module.board.viewmodel.OptionListViewModel

class OptionListActivity: BaseAppActivity<OptionListViewModel, ActivityOptionListBinding>() {

    private val mAdapter = OptionListAdapter {
        // 操作详情
        startTargetActivity<OptionActivity>(
            bundleOf(
                ACTION_OPTION_LIST to it,
                ACTION_OPTION to viewModel.mOptionBean,
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_list)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewModel.mOptionBean = intent.getParcelableExtra(ACTION_OPTION, OptionBean::class.java)
        } else {
            viewModel.mOptionBean = intent.getParcelableExtra(ACTION_OPTION)
        }

        mBinding.workOrder.bean = viewModel.mOptionBean
        initList()
        mBinding.refreshLayout.autoRefresh()
    }

    private fun initList() {
        mBinding.mRecyclerView.layoutManager = LinearLayoutManager(this)
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
        viewModel.mFinishLiveData.observe(this) {
            if (it)
                finish()
        }
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
        LiveEventBus.get(BUS_REFRESH_OPTION, Boolean::class.java).observe(this) {
            mBinding.refreshLayout.autoRefresh()
        }
    }
}