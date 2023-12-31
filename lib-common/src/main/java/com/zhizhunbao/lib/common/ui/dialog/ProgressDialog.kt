@file:Suppress("unused")

package com.zhizhunbao.lib.common.ui.dialog

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.zhizhunbao.lib.common.R
import com.zhizhunbao.lib.common.base.BaseAppDialog
import com.zhizhunbao.lib.common.databinding.AppDialogProgressBinding
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.lifecycle.AppDialogObserver
import com.zhizhunbao.lib.common.viewmodel.ProgressViewModel

/**
 * 进度条弹窗
 *
 */
class ProgressDialog
    : BaseAppDialog<ProgressViewModel, AppDialogProgressBinding>() {

    override val viewModel: ProgressViewModel by viewModels()

    override val layoutResId = R.layout.app_dialog_progress

    override fun initView() {
        // 从 argument 中获取数据
        val arguments = arguments ?: return
        // 能否取消
        isCancelable = arguments.getBoolean(ACTION_EVENT_CANCELABLE, true).safe()
        // 提示文本
        val hint = arguments.getString(ACTION_EVENT_HINT, "").orEmpty()
        if (hint.isNotBlank()) {
            viewModel.hintStr.set(hint)
        }
    }

    override fun initObserve() {
        // 空白处点击
        viewModel.blankClickData.observe(this) {
            if (isCancelable) {
                dismiss()
            }
        }
    }

    companion object {

        /** 能否取消 */
        private const val ACTION_EVENT_CANCELABLE = "action_event_cancelable"

        /** 提示文本 */
        private const val ACTION_EVENT_HINT = "action_event_hint"

        /**
         * 使用 [activity] 创建显示并返回 [ProgressDialog]，传递参数能否取消[cancelable]&提示文本[hint]
         * > [cancelable] 默认 `true` [hint] 默认 `""`
         */
        fun actionShow(
            activity: FragmentActivity,
            cancelable: Boolean = true,
            hint: String = ""
        ): ProgressDialog {
            return ProgressDialog().apply {
                arguments = bundleOf(
                    ACTION_EVENT_CANCELABLE to cancelable,
                    ACTION_EVENT_HINT to hint
                )
                val observer = AppDialogObserver(::dismiss)
                val lifecycleOwner = activity as? LifecycleOwner
                lifecycleOwner?.lifecycle?.addObserver(observer)
                show(activity)
            }
        }
    }
}