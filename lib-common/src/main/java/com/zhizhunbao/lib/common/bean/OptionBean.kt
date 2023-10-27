package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 操作
 */
@Parcelize
data class OptionBean(
    var Id: String? = null,
    var Co: String? = null,
    var Category: String? = null,
    var No: String? = null,
    var Descr: String? = null,
    var Station: String? = null,
    var Status: String? = null,
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
) : Parcelable {
    fun getFinishString() = Finish.safe(0).toString().plus("/")
    fun getPlanString() = Plan.safe(1).toString()
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
    var radioValue: String? = null,
    var inputValue: String? = null,
    var checkValue: MutableList<String>? = null,
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