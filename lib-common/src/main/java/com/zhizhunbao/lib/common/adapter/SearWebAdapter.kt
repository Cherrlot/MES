package com.zhizhunbao.lib.common.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import com.zhizhunbao.lib.common.ext.safe

/**
 * 不过滤任何结果
 */
class SearWebAdapter(context: Context?, resource: Int, private var mObjects: List<String>) :
    ArrayAdapter<String?>(context!!, resource, mObjects) {
    private var mFilter: Filter? = null
    override fun getFilter(): Filter {
        if (mFilter == null) {
            mFilter = HintFilter()
        }
        return mFilter!!
    }

    /**
     *
     * An array filter constrains the content of the array adapter with
     * a prefix. Each item that does not start with the supplied prefix
     * is removed from the list.
     * 重写过滤类 自定义一个不会过滤任何数的Filter
     */
    private inner class HintFilter : Filter() {
        override fun performFiltering(prefix: CharSequence): FilterResults {
            val suggestions = ArrayList<Any>()
            for (s in mObjects) {
                suggestions.add(s)
            }
            val filterResults = FilterResults()
            filterResults.values = suggestions
            filterResults.count = suggestions.size
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            mObjects = if (results?.values != null) {
                results.values as List<String>
            } else {
                mutableListOf()
            }
            if (results?.count.safe() > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }
}