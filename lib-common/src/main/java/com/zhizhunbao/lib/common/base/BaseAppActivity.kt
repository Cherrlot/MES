package com.zhizhunbao.lib.common.base

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.zhizhunbao.lib.common.BR
import com.zhizhunbao.lib.common.R
import com.zhizhunbao.lib.common.constant.ACTIVITY_ANIM_DURATION
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.tool.color
import com.zhizhunbao.lib.common.tool.fixFontScaleResources
import com.zhizhunbao.lib.common.ui.dialog.ProgressDialog
import com.zhizhunbao.lib.common.util.SysUtils


abstract class BaseAppActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseActivity() {
    /** 当前界面 ViewModel 对象 */
    protected lateinit var viewModel: VM

    /** DataBinding 对象 */
    protected lateinit var mBinding: DB

    /**
     * 等待弹窗
     */
    private var mDialog: ProgressDialog? = null

    /**
     * 网络请求信息提示
     */
    private var mNetInfoView: View? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initStatusBar()

        viewModel = ViewModelProvider(this).get(SysUtils.getClass(this))
        // 初始化观察者
        initObserve()
        // 添加观察者
        observeData()
    }

    override fun getResources(): Resources? {
        // 修复 app 字体大小跟随系统字体大小调节
        return fixFontScaleResources(super.getResources(), this)
    }

    /** [onCreate] 之前执行，可用于配置动画 */
    protected open fun beforeOnCreate() {
        window.run {
            enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
                duration = ACTIVITY_ANIM_DURATION
            }
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
                duration = ACTIVITY_ANIM_DURATION
            }
        }
    }

    /** 初始化状态栏相关配置 */
    protected fun initStatusBar() {
        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController
                ?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
        } else {
            // 状态栏白色字体
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            // 状态栏黑色字体
//            window.decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = R.color.app_colorPrimary.color
    }

    /**
     * 初始化观察者
     */
    protected open fun initObserve() {
    }

    override fun setContentView(layoutResID: Int) {
        // 初始化 DataBinding
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            layoutResID, null, false
        )

        // 绑定生命周期管理
        mBinding.lifecycleOwner = this

        // 绑定 ViewModel
        mBinding.setVariable(BR.viewModel, viewModel)

        // 设置布局
        super.setContentView(mBinding.root)
    }

    /** 添加观察者 */
    private fun observeData() {
        // UI 关闭
        viewModel.uiCloseData.observe(this) { close ->
            close?.let { model ->
                setResult(model.resultCode, model.result)
                finish()
            }
        }
        // 界面跳转
        viewModel.uiNavigationData.observe(this) { path ->
            ARouter.getInstance().build(path).navigation(mContext)
        }
        // 等待框
        viewModel.isLoading.observe(this) {
            if (it) {
                // 显示等待框
                if (mDialog == null) {
                    mDialog = ProgressDialog.actionShow(this, false, viewModel.mMsg.value.safe())
                } else if (!mDialog?.isAdded.safe()) {
                    mDialog?.show(this)
                }
            } else {
                // 隐藏等待框
                mDialog?.dismiss()
            }
        }
        // 更改提示view的状态
        viewModel.netRequestState.observe(this) {
            var imgId: Int = R.drawable.default_nonetwork
            var msg = getString(R.string.network_error)
            when (it.code) {
                StateType.NETWORK_ERROR -> {
                    // 网络错误
                    imgId = R.drawable.default_nonetwork
                    msg = getString(R.string.network_error)
                    mNetInfoView?.visibility = View.VISIBLE
                }

                StateType.EMPTY -> {
                    // 没有数据
                    imgId = R.drawable.default_nodetail
                    msg = getString(R.string.no_data_error)
                    mNetInfoView?.visibility = View.VISIBLE
                }

                StateType.ERROR -> {
                    // 其他错误
                    imgId = R.drawable.default_no_data
                    msg = it.message
                    mNetInfoView?.visibility = View.VISIBLE
                }

                StateType.SUCCESS -> {
                    // 成功
                    mNetInfoView?.visibility = View.GONE
                }
            }

            mNetInfoView?.apply {
                if (visibility == View.VISIBLE) {
                    findViewById<TextView>(R.id.msg).text = msg
                    findViewById<ImageView>(R.id.img).setImageResource(imgId)
                }
            }
        }
    }

    /**
     * 添加一个提示view
     * @param reload    点击后重新请求
     * @param parentView    需要添加提示view的父view
     */
    open fun addInfoView(parentView: ViewGroup, reload: () -> Unit) {
        if (null == mNetInfoView) {
            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            mNetInfoView = LayoutInflater.from(this).inflate(R.layout.layout_load_feed, null)
            mNetInfoView?.visibility = View.GONE
            parentView.addView(mNetInfoView, parentView.childCount, layoutParams)
        }
        mNetInfoView?.setOnClickListener {
            reload()
        }
    }
}