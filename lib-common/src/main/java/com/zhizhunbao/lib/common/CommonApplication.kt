package com.zhizhunbao.lib.common

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import com.zhizhunbao.lib.common.tool.color
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.header.MaterialHeader.SIZE_LARGE
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.zhizhunbao.lib.common.R
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.onAdaptListener
import me.jessyan.autosize.utils.ScreenUtils
import kotlin.properties.Delegates

abstract class CommonApplication : MultiDexApplication() {
    companion object {
        var instance: CommonApplication by Delegates.notNull()

        //static 代码段可以防止内存泄露
        init {
            //设置全局默认配置（优先级最低，会被其他设置覆盖）
            SmartRefreshLayout.setDefaultRefreshInitializer { _: Context?, layout: RefreshLayout ->
                //开始设置全局的基本参数
                layout.setFooterHeight(40f)
                layout.setDisableContentWhenLoading(false)
                layout.setDisableContentWhenRefresh(true) //是否在刷新的时候禁止列表的操作
                layout.setDisableContentWhenLoading(true) //是否在加载的时候禁止列表的操作
                layout.setEnableOverScrollBounce(true)
            }
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, _: RefreshLayout? ->
                MaterialHeader(context)
                    .setSize(SIZE_LARGE)
                    .setProgressBackgroundColorSchemeColor(R.color.app_colorPrimary.color)
                    .setColorSchemeResources(R.color.app_white)
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, _: RefreshLayout? ->
                ClassicsFooter(context)
                    .setSpinnerStyle(SpinnerStyle.Translate)
                    .setTextSizeTitle(13f)
                    .setDrawableArrowSize(15f)
                    .setDrawableProgressSize(15f)
                    .setDrawableMarginRight(10f)
                    .setFinishDuration(0)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AutoSizeConfig.getInstance().onAdaptListener = object : onAdaptListener{
            override fun onAdaptBefore(target: Any?, activity: Activity?) {
                //使用以下代码, 可以解决横竖屏切换时的屏幕适配问题
                //首先设置最新的屏幕尺寸，ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application !!!
                AutoSizeConfig.getInstance().screenWidth = ScreenUtils.getScreenSize(activity)[0]
                AutoSizeConfig.getInstance().screenHeight = ScreenUtils.getScreenSize(activity)[1]
                //根据屏幕方向，设置设计尺寸
                if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    //设置横屏设计尺寸
                    AutoSizeConfig.getInstance()
                        .setDesignWidthInDp(1000).designHeightInDp = 600
                } else {
                    //设置竖屏设计尺寸
                    AutoSizeConfig.getInstance()
                        .setDesignWidthInDp(400).designHeightInDp = 666
                }
            }

            override fun onAdaptAfter(target: Any?, activity: Activity?) {
            }
        }
    }
}