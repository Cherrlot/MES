package com.zhizhunbao.lib.common.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeremyliao.liveeventbus.LiveEventBus
import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.bean.UpdateBean
import com.zhizhunbao.lib.common.constant.BUS_CHANGE_DINING
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.ext.toast
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.net.initiateRequest
import com.zhizhunbao.lib.common.repository.DishRepository
import com.zhizhunbao.lib.common.repository.UserRepository
import com.zhizhunbao.lib.common.util.DeleteFilesUtil
import com.zhizhunbao.lib.common.util.LogSaveUtil
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.io.File

class SplashViewModel : BaseViewModel() {
    private val mDishRepository: DishRepository by inject(DishRepository::class.java)
    private val mRepository: UserRepository by inject(UserRepository::class.java)
    private var mPath: String = ""

    /**
     * 检查更新
     */
    var isUpdateOk = MutableLiveData<Boolean>().apply { this.value = false }

    /**
     * 获取用户信息
     */
    var isGetAccountInfoOk = MutableLiveData<Boolean>().apply { this.value = false }

    /**
     * 是否初始化完成
     */
    var isInitOk = MutableLiveData<Boolean>().apply { this.value = false }

    /**
     * 检查更新
     */
    var mUpdateData = MutableLiveData<UpdateBean>()

    /**
     * 保存日志到文件
     */
    fun initLogSave(context: Context) {
        mPath = "${context.getExternalFilesDir(null)?.path}/log/"
        val logFolder = File(mPath)
        if (!logFolder.exists()) {
            logFolder.mkdirs()
        }
        LogSaveUtil.TAG = BuildConfig.PRINT_LOG_TAG
        LogSaveUtil.createLogCollector(logFolder.absolutePath)

        viewModelScope.launch { deleteOldFile() }
    }

    /**
     * 删除过期的文件
     */
    private fun deleteOldFile() {
        DeleteFilesUtil.deleteFiles(mPath, 5)
        isInitOk.postValue(true)
    }

    /**
     * 检查更新
     */
    fun checkUpdate() {
        initiateRequest({
            mRepository.checkAppUpdate("app.version")
        },
            success = {
                mUpdateData.value = it?.get(0)
            },
            failed = { _: String?, _: StateType ->
                isUpdateOk.value = true
            })
    }

    /**
     * 获取用户信息
     */
    fun getAccountInfo() {
        initiateRequest({ mDishRepository.getAccountInfo() },
            success = {
                AppLocalData.accountInfo = it
                isGetAccountInfoOk.value = true
            },
            failed = { s: String?, _: StateType ->
                "获取用户信息失败：$s".toast()
                isGetAccountInfoOk.value = true
            })
    }
}