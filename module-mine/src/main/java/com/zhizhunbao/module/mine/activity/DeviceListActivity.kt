package com.zhizhunbao.module.mine.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.bean.MachineBean
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.State
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.websocket.AppWebsocket
import com.zhizhunbao.module.mine.R
import com.zhizhunbao.module.mine.adapter.MachineListAdapter
import com.zhizhunbao.module.mine.databinding.ActivityDeviceListBinding
import com.zhizhunbao.module.mine.viewmodel.DeviceListViewModel

class DeviceListActivity: BaseAppActivity<DeviceListViewModel, ActivityDeviceListBinding>() {
    private val mAdapter = MachineListAdapter { it, position->
        // 选择当前设备
        if (AppLocalData.machineNo != it.No) {
            confirmDialog(it, position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

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
        viewModel.mMachineList.observe(this) {
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
    }

    private fun confirmDialog(selectedMachine: MachineBean, position: Int) {
        MaterialDialog(this).show {
            title(text = "是否选择当前设备？")
        }.positiveButton {
            mAdapter.changeSelect(position)
            AppLocalData.machineNo = selectedMachine.No.safe()
            AppLocalData.workplace = selectedMachine.Workplace.safe()
            AppWebsocket.appWebsocketConnect()
        }.negativeButton {  }
    }
}