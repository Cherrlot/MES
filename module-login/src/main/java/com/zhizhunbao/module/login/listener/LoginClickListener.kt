package com.zhizhunbao.module.login.listener

interface LoginClickListener {
    /**
     * 登录
     */
    fun onLoginClick()

    /**
     * 注册
     */
    fun onRegisterClick(isRegister: Boolean)
}