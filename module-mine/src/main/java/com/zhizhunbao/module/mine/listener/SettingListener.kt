package com.zhizhunbao.module.mine.listener

/**
 * 设置点击事件
 */
interface SettingListener {
    /**
     * 退出登录
     */
    fun onQuitClick()
    /**
     * 修改密码
     */
    fun onPwdClick()
}