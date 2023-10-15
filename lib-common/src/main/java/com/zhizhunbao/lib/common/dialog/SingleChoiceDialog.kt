package com.zhizhunbao.lib.common.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Parcelable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhizhunbao.lib.common.R
import com.zhizhunbao.lib.common.base.BaseAppAdapter
import com.zhizhunbao.lib.common.base.BaseViewHolder
import com.zhizhunbao.lib.common.databinding.ItemSingleChoiceBinding
import com.zhizhunbao.lib.common.ext.hideSoftKeyboard
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize


/**
 * 单选dialog
 */
class SingleChoiceDialog(var context: Activity) {
    private var mAdapter = SingleChoiceAdapter()

    private var alertDialog: AlertDialog? = null
    private var titleView: TextView? = null
    private var messageView: TextView? = null
    private var cancelView: TextView? = null
    private var confirmView: TextView? = null
    private var mRecyclerView: RecyclerView? = null
    private val mDataList: MutableList<SingleChoiceBean> = mutableListOf()

    init {
        //设置弹框属性
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //加载布局文件
        val view = inflater.inflate(R.layout.dialog_single_choise, null, false)

        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setView(view)
        alertDialog = alertBuilder.create()

        //设置布局
        titleView = view.findViewById(R.id.tv_title)
        messageView = view.findViewById(R.id.tv_content)
        cancelView = view.findViewById(R.id.tv_cancel)
        confirmView = view.findViewById(R.id.tv_ok)
        mRecyclerView = view.findViewById(R.id.mRecyclerView)
        cancelView?.setOnClickListener { dismiss() }
        confirmView?.setOnClickListener { dismiss() }

        val linearLayoutManager = LinearLayoutManager(context)
        mRecyclerView?.layoutManager = linearLayoutManager
        mRecyclerView?.adapter = mAdapter
    }

    fun <T : SingleChoiceBean> addData(data: T) {
        mAdapter.setList(mutableListOf(data))
    }

    fun <T : SingleChoiceBean> addDatas(datas: MutableList<T>) {
        mAdapter.setList(datas)
    }

    fun setTitle(resId: Int): SingleChoiceDialog {
        titleView?.visibility = View.VISIBLE
        messageView?.visibility = View.GONE
        titleView?.setText(resId)
        return this@SingleChoiceDialog
    }

    fun setTitle(title: String): SingleChoiceDialog {
        titleView?.visibility = View.VISIBLE
        messageView?.visibility = View.GONE
        titleView?.text = title
        return this@SingleChoiceDialog
    }

    fun setTitleSize(textSize: Float): SingleChoiceDialog {
        titleView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        return this@SingleChoiceDialog
    }

    fun setTitleColor(resId: Int): SingleChoiceDialog {
        titleView?.setTextColor(resId)
        return this@SingleChoiceDialog
    }

    fun setTitleGravity(gravity: Int): SingleChoiceDialog {
        titleView?.gravity = gravity
        return this@SingleChoiceDialog
    }

    fun setMessageSize(textSize: Float): SingleChoiceDialog {
        messageView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        return this@SingleChoiceDialog
    }

    fun setMessageColor(resId: Int): SingleChoiceDialog {
        messageView?.setTextColor(resId)
        return this@SingleChoiceDialog
    }


    fun setMessage(resId: Int): SingleChoiceDialog {
        messageView?.visibility = View.VISIBLE
        messageView?.setText(resId)
        return this@SingleChoiceDialog
    }

    fun setMessage(message: String): SingleChoiceDialog {
        messageView?.visibility = View.VISIBLE
        messageView?.text = message
        return this@SingleChoiceDialog
    }

    fun setMessageGravity(gravity: Int): SingleChoiceDialog {
        messageView?.gravity = gravity
        return this@SingleChoiceDialog
    }


    /**
     * 设置取消按钮
     */
    fun setNegativeButton(text: String = "取消", autoDismiss: Boolean = true, listener: (() -> Unit)? = null): SingleChoiceDialog {
        cancelView?.text = text
        cancelView?.setOnClickListener {
            if (autoDismiss)
                dismiss()
            listener?.invoke()
        }

        return this@SingleChoiceDialog
    }

    /**
     * 设置确定按钮
     */
    fun setPositiveButton(text: String = "确定", autoDismiss: Boolean = true, listener: ((Int) -> Unit)?): SingleChoiceDialog {
        confirmView?.text = text
        confirmView?.setOnClickListener {
            if (autoDismiss)
                dismiss()
            listener?.invoke(mAdapter.getSelectIndex())
        }

        return this@SingleChoiceDialog
    }

    fun setDismissListener(listener: (() -> Unit)?) {
        alertDialog?.setOnDismissListener {
            listener?.invoke()
        }
    }

    fun setCanceledOnTouchOutside(boolean: Boolean): SingleChoiceDialog {
        alertDialog?.setCanceledOnTouchOutside(false)
        return this@SingleChoiceDialog
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
        func: SingleChoiceDialog.() -> Unit,
    ): SingleChoiceDialog = apply {
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

    fun isShowing(): Boolean {
        return alertDialog?.isShowing.safe()
    }
}
@Parcelize
open class SingleChoiceBean(
    var name: String? = ""
) : Parcelable

class SingleChoiceAdapter: BaseAppAdapter<ItemSingleChoiceBinding, SingleChoiceBean>() {

    /**选中的位置集合*/
    private val checkIndexList = mutableListOf<Int>()

    /** 布局 id */
    override val layoutResID: Int
        get() = R.layout.item_single_choice

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = listDatas[holder.absoluteAdapterPosition]
        (holder.mBinding as ItemSingleChoiceBinding).let { mBinding ->
            mBinding.bean = data

            // 点击
            mBinding.root.setOnClickListener {
                itemClick(holder.absoluteAdapterPosition)
            }
            mBinding.cbCheck.isChecked = checkIndexList.contains(position)
        }
    }

    /**
     * 条目点击处理
     */
    private fun itemClick(position: Int) {
        if (checkIndexList.contains(position)) {
            return
        } else {
            checkIndexList.clear()
            checkIndexList.add(position)
        }
        notifyItemChanged(position)
    }

    fun getSelectIndex() : Int{
        return if (checkIndexList.size == 0)
             -1
        else
            checkIndexList[0]
    }
}