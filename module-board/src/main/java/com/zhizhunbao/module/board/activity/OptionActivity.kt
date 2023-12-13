package com.zhizhunbao.module.board.activity

import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhizhunbao.lib.common.base.BaseAppActivity
import com.zhizhunbao.lib.common.bean.OptionBean
import com.zhizhunbao.lib.common.bean.OptionItemBean
import com.zhizhunbao.lib.common.bean.OptionListBean
import com.zhizhunbao.lib.common.constant.ACTION_OPTION
import com.zhizhunbao.lib.common.constant.ACTION_OPTION_LIST
import com.zhizhunbao.lib.common.constant.SdkValue
import com.zhizhunbao.lib.common.dialog.SingleChoiceDialog
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.setOnThrottleClickListener
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.manager.BluetoothManager
import com.zhizhunbao.lib.common.manager.BluetoothStatusChangeListener
import com.zhizhunbao.lib.common.websocket.AppWebsocket
import com.zhizhunbao.module.board.R
import com.zhizhunbao.module.board.adapter.OptionInfoAdapter
import com.zhizhunbao.module.board.databinding.ActivityOptionBinding
import com.zhizhunbao.module.board.view.OptionCheckView
import com.zhizhunbao.module.board.view.OptionGroupView
import com.zhizhunbao.module.board.view.OptionInputView
import com.zhizhunbao.module.board.view.OptionRadioView
import com.zhizhunbao.module.board.view.OptionTextView
import com.zhizhunbao.module.board.viewmodel.OptionViewModel


/**
 * 操作表单
 */
class OptionActivity : BaseAppActivity<OptionViewModel, ActivityOptionBinding>() {
    private var mSuccessAudio = ""
    private var mFailAudio = ""

    /** 父view*/
    private var mRootView: ViewGroup? = null

    private val mAdapter = OptionInfoAdapter()

    private val mMediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        mSuccessAudio = "android.resource://" + packageName + "/" + com.zhizhunbao.lib.common.R.raw.ok
        mFailAudio = "android.resource://" + packageName + "/" + com.zhizhunbao.lib.common.R.raw.error

        initBluetooth()

        mRootView = mBinding.clContent

        mBinding.mRecyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.mRecyclerView.adapter = mAdapter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewModel.mOptionListBean.value =
                intent.getParcelableExtra(ACTION_OPTION_LIST, OptionListBean::class.java)
        } else {
            viewModel.mOptionListBean.value = intent.getParcelableExtra(ACTION_OPTION_LIST)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewModel.mOptionBean.value =
                intent.getParcelableExtra(ACTION_OPTION, OptionBean::class.java)
        } else {
            viewModel.mOptionBean.value = intent.getParcelableExtra(ACTION_OPTION)
        }
    }

    private fun initBluetooth() {
        BluetoothManager.builder(SingleChoiceDialog(this).apply {
            setTitle("请选择扫码枪")
            setPositiveButton(listener = {
                BluetoothManager.connectDevice(it)
            })
            setNegativeButton(listener = {
                BluetoothManager.cancelDiscovery()
            })
            setCanceledOnTouchOutside(false)
        }).addStatusChangeListener(object :
            BluetoothStatusChangeListener {
            override fun onConnect() {
                // 永不休眠
                BluetoothManager.sendCommand(SdkValue.Sleeptime_I)
            }
        }).startRead(resultListener = { code ->
            // 扫码提交
            AppLog.d("扫码提交：$code")
            viewModel.scanAndSubmit(code)
        }, onError = {
            viewModel.showLoading()
            mRootView?.postDelayed({
                viewModel.hideLoading()
                initBluetooth()
            }, 500)
            it.toast()
        })
    }

    private fun successSound() {
        try {
            mMediaPlayer.reset()
            mMediaPlayer.setDataSource(this, Uri.parse(mSuccessAudio))
            mMediaPlayer.prepare()
            mMediaPlayer.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun failSound() {
        try {
            mMediaPlayer.reset()
            mMediaPlayer.setDataSource(this, Uri.parse(mFailAudio))
            mMediaPlayer.prepare()
            mMediaPlayer.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        mMediaPlayer.release()
        super.onDestroy()
    }

    private fun initView() {
        mBinding.toolbar.title = viewModel.mOptionListBean.value?.Descr
        viewModel.mOptionListBean.value?.Items?.let { options ->
            options.forEach { optionItemBean ->
                val group = optionItemBean.groups
                if (!group.isNullOrEmpty()) {
                    val groupView = OptionGroupView(this, optionItemBean)
                    mRootView?.addView(groupView, mRootView?.childCount.safe())
                    return@forEach
                }
                when (optionItemBean.type) {
                    "input" -> addInputView(optionItemBean)
                    "radio" -> addRadioView(optionItemBean)
                    "checkbox" -> addCheckboxView(optionItemBean)
                    else -> addTextView(optionItemBean)
                }
            }
        }
        mBinding.btnSave.setOnThrottleClickListener({
            // 提交
            viewModel.submit()
        })
    }

    /**
     * 添加文本框
     */
    private fun addTextView(optionItemBean: OptionItemBean) {
        val textView = OptionTextView(this, optionItemBean)
        mRootView?.addView(textView, mRootView?.childCount.safe())
    }

    /**
     * 添加输入框
     */
    private fun addInputView(optionItemBean: OptionItemBean) {
        val inputView = OptionInputView(this, optionItemBean)
        mRootView?.addView(inputView, mRootView?.childCount.safe())
    }

    /**
     * 添加单选框
     */
    private fun addRadioView(optionItemBean: OptionItemBean) {
        val radioView = OptionRadioView(this, optionItemBean)
        mRootView?.addView(radioView, mRootView?.childCount.safe())
    }

    /**
     * 添加复选框
     */
    private fun addCheckboxView(optionItemBean: OptionItemBean) {
        val checkView = OptionCheckView(this, optionItemBean)
        mRootView?.addView(checkView, mRootView?.childCount.safe())
    }

    override fun initObserve() {
        viewModel.mResultLiveData.observe(this) {
            if (it)
                successSound()
            else
                failSound()
        }
        viewModel.mOptionBean.observe(this) {
            mBinding.workOrder.bean = it
            mAdapter.setList(it?.info)
        }
        viewModel.mOptionListBean.observe(this) {
            initView()
        }
        viewModel.mFinishLiveData.observe(this) {
            if (it)
                finish()
        }
    }
}