package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 操作
 * 袋子型号：Category
 * 气柱规格：Spec
 * 柱宽：Colswidth
 * 下单数量：Qty
 * 损耗：Los
 * 节点：Splits
 * 生成交期：Delivery
 * 包装要求：Package
 * 生成备注：Remark
 * 型号：Product
 * 用料：Key1，Key2连个字段，中间用*连接
 * 柱数量：Cols
 *
 */
@Parcelize
data class OptionBean(
    var Id: String? = null,
    var Co: String? = null,
    var Category: String? = null,
    var No: String? = null,
    var Spec: String? = null,
    var Colswidth: String? = null,
    var Qty: String? = null,
    var Splits: String? = null,
    var Delivery: String? = null,
    var Package: String? = null,
    var Los: String? = null,
    var Descr: String? = null,
    var Station: String? = null,
    var Status: String? = null,
    var Key1: String? = null,
    var Key2: String? = null,
    var Cols: String? = null,
    var Uname: String? = null,
    var Workplace: String? = null,
    var Machine: String? = null,
    var Orderno: String? = null,
    var Product: String? = null,
    var Remark: String? = null,
    var info: MutableList<OptionInfoBean>? = null,
    var Seq: Int? = null,
    var Plan: Int? = null,
    var Finish: Int? = null,
    var steps: MutableList<OptionListBean>? = null,
    var keys: MutableList<String>? = null,
    var details: MutableList<String>? = null,
) : Parcelable {
    fun getFinishString() = Finish.safe(0).toString().plus("/")
    fun getPlanString() = Plan.safe(1).toString()
    fun getMaterialString() = Key1.safe().plus(" * ").plus(Key2)
}

@Parcelize
data class OptionListBean(
    var Id: String? = null,
    var Co: String? = null,
    var Station: String? = null,
    var Status: String? = null,
    var Descr: String? = null,
    var Category: String? = null,
    /**
     * picking: 生产领料
     * packaging:完工打包
     * inbound:扫码入库
     * feeding:生产上料
     */
    var No: String? = null,
    var Seq: Int? = null,
    var Items: MutableList<OptionItemBean>? = null,
) : Parcelable

@Parcelize
data class OptionItemBean(
    var title: String? = null,
    var type: String? = null,
    var value: String? = null,
    var field: String? = null,
    var format: String? = null,
    var radioValue: String? = null,
    var inputValue: String? = null,
    var groupValue: String? = null,
    var checkValue: MutableList<String>? = null,
    var options: MutableList<String>? = null,
    var groups: MutableList<OptionGroupItemBean>? = null,
) : Parcelable


@Parcelize
data class OptionGroupItemBean(
    var group: String? = null,
    var options: MutableList<String>? = null,
) : Parcelable

@Parcelize
data class OptionInfoBean(
    var group: String? = null,
    var time: MutableList<String>? = null,
    var label: String? = null,
    var date: String? = null,
): Parcelable

@Parcelize
data class OptionInfoItemBean(
    var content: String? = null,
    var label: String? = null,
    var date: String? = null,
): Parcelable