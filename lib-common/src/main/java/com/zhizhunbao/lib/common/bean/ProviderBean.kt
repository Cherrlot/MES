package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.contrarywind.interfaces.IPickerViewData
import com.zhizhunbao.lib.common.ext.safe
import kotlinx.parcelize.Parcelize

/**
 * 供应商
 */
@Parcelize
data class ProviderBean(
    var id: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var purchaseBuyerId: String? = null,
) : Parcelable, IPickerViewData{
    override fun getPickerViewText() = name.safe()
}

/**
 * 采购员
 */
@Parcelize
data class SortingBean(
    var id: String? = null,
    var name: String? = null,
    var phone: String? = null,
) : Parcelable

