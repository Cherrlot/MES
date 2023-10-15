package com.zhizhunbao.lib.common.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.zhizhunbao.lib.common.R
import com.zhizhunbao.lib.common.ext.hideSoftKeyboard
import com.zhizhunbao.lib.common.ext.safe


/**
 * 自定义dialog
 */
class CustomNormalDialog(var context: Activity) {
    private var alertDialog: AlertDialog? = null
    private var titleView: TextView? = null
    private var messageView: TextView? = null
    private var cancelView: TextView? = null
    private var confirmView: TextView? = null
    var contentView: FrameLayout? = null

    /** 自定义view**/
    var customView: View? = null

    init {
        //设置弹框属性
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //加载布局文件
        val view = inflater.inflate(R.layout.dialog_base, null, false)

        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setView(view)
        alertDialog = alertBuilder.create()

        //设置布局
        titleView = view.findViewById(R.id.tv_title)
        messageView = view.findViewById(R.id.tv_content)
        cancelView = view.findViewById(R.id.tv_cancel)
        confirmView = view.findViewById(R.id.tv_ok)
        contentView = view.findViewById(R.id.fl_content)
        cancelView?.setOnClickListener { dismiss() }
        confirmView?.setOnClickListener { dismiss() }
    }

    fun setTitle(resId: Int): CustomNormalDialog {
        titleView?.visibility = View.VISIBLE
        messageView?.visibility = View.GONE
        titleView?.setText(resId)
        return this@CustomNormalDialog
    }

    fun setTitle(title: String): CustomNormalDialog {
        titleView?.visibility = View.VISIBLE
        messageView?.visibility = View.GONE
        titleView?.text = title
        return this@CustomNormalDialog
    }

    fun setTitleSize(textSize: Float): CustomNormalDialog {
        titleView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        return this@CustomNormalDialog
    }

    fun setTitleColor(resId: Int): CustomNormalDialog {
        titleView?.setTextColor(resId)
        return this@CustomNormalDialog
    }

    fun setTitleGravity(gravity: Int): CustomNormalDialog {
        titleView?.gravity = gravity
        return this@CustomNormalDialog
    }

    fun setMessageSize(textSize: Float): CustomNormalDialog {
        messageView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        return this@CustomNormalDialog
    }

    fun setMessageColor(resId: Int): CustomNormalDialog {
        messageView?.setTextColor(resId)
        return this@CustomNormalDialog
    }


    fun setMessage(resId: Int): CustomNormalDialog {
        messageView?.visibility = View.VISIBLE
        messageView?.setText(resId)
        return this@CustomNormalDialog
    }

    fun setMessage(message: String): CustomNormalDialog {
        messageView?.visibility = View.VISIBLE
        messageView?.text = message
        return this@CustomNormalDialog
    }

    fun setMessageGravity(gravity: Int): CustomNormalDialog {
        messageView?.gravity = gravity
        return this@CustomNormalDialog
    }


    /**
     * 设置取消按钮
     */
    fun setNegativeButton(text: String = "取消", autoDismiss: Boolean = true, listener: (() -> Unit)? = null): CustomNormalDialog {
        cancelView?.text = text
        cancelView?.setOnClickListener {
            if (autoDismiss)
                dismiss()
            listener?.invoke()
        }

        return this@CustomNormalDialog
    }

    /**
     * 设置确定按钮
     */
    fun setPositiveButton(text: String = "确定", autoDismiss: Boolean = true, listener: (() -> Unit)?): CustomNormalDialog {
        confirmView?.text = text
        confirmView?.setOnClickListener {
            if (autoDismiss)
                dismiss()
            listener?.invoke()
        }

        return this@CustomNormalDialog
    }

    fun setCanceledOnTouchOutside(boolean: Boolean): CustomNormalDialog {
        alertDialog?.setCanceledOnTouchOutside(false)
        return this@CustomNormalDialog
    }

    /**
     * 打开对话框
     */
    fun show(width: Int = context.resources.getDimension(R.dimen.DIMEN_335DP).toInt()) {
        if (!isShowing()) {
            alertDialog?.show()

            val window = alertDialog?.window
            window?.setBackgroundDrawableResource(R.drawable.shape_white_r5_bg)
            val attributes = window?.attributes
            attributes?.width = width
            window?.attributes = attributes
        }
    }

    /**
     * 打开对话框
     * @param width 弹窗宽度
     */
    inline fun show(
        width: Int = context.resources.getDimension(R.dimen.DIMEN_335DP).toInt(),
        func: CustomNormalDialog.() -> Unit,
    ): CustomNormalDialog = apply {
        this.func()
        this.show(width)
    }

    /**
     * 关闭对话框
     */
    fun dismiss() {
        if (isShowing()) {
            context.currentFocus.hideSoftKeyboard()
            alertDialog?.dismiss()
        }
    }

    fun setCancelable(cancelable: Boolean) {
        alertDialog?.setCancelable(cancelable)
    }

    private fun isShowing(): Boolean {
        return alertDialog?.isShowing.safe()
    }
}

fun CustomNormalDialog.getCustomView(): View? {
    return this.customView
}

/**
 * 设置自定义view
 */
fun CustomNormalDialog.customView(view: View, callBack: ((View) -> Unit)? = null): CustomNormalDialog {
    this.contentView?.removeAllViews()
    this.customView = view
    this.contentView?.addView(view)
    callBack?.invoke(view)
    return this
}