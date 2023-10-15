package com.zhizhunbao.lib.common.customeview.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * 可以获取当前fragment的adapter
 */
abstract class AppFragmentStateAdapter(private val fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    /**
     * 获取当前fragment
     */
    fun getFragment(id: Long): Fragment? {
        return fragmentActivity.supportFragmentManager.findFragmentByTag("f${id}")
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}