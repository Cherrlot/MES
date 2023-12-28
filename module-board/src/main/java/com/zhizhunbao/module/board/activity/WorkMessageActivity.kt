package com.zhizhunbao.module.board.activity

import android.os.Build
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.bean.OptionBean
import com.zhizhunbao.lib.common.constant.ACTION_OPTION
import com.zhizhunbao.module.board.databinding.ActivityWorkMessageBinding
import com.zhizhunbao.module.board.viewmodel.OptionViewModel
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.adapter.OptionListAdapter
import com.zhizhunbao.module.board.adapter.WorkDetailAdapter

/**
 * 工单详细信息
 */
class WorkMessageActivity : BaseAppActivity<OptionViewModel, ActivityWorkMessageBinding>(){
    private val mAdapter = WorkDetailAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_message)

        val data: OptionBean?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            data = intent.getParcelableExtra(ACTION_OPTION, OptionBean::class.java)
        } else {
            data = intent.getParcelableExtra(ACTION_OPTION)
        }

//        mBinding.bean = data
        mBinding.mRecyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.mRecyclerView.adapter = mAdapter
        mAdapter.setList(data?.details)
    }

    override fun initObserve() {
        viewModel.mFinishLiveData.observe(this) {
            if (it)
                finish()
        }
    }
}