package com.zhizhunbao.lib.common.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zhizhunbao.lib.common.tool.dpi

class GridItemDecoration(private var space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = 10.dpi
        outRect.left = space / 2
        outRect.right = space / 2
    }
}