package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.contrarywind.interfaces.IPickerViewData
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.util.TimeUtils
import kotlinx.parcelize.Parcelize

@Parcelize
class TypeBean(
    var id: String? = null,
    var parentId: String? = "",
    var name: String? = "",
    var status: Int? = null,
) : Parcelable, IPickerViewData {
    override fun getPickerViewText() = name.safe()
}