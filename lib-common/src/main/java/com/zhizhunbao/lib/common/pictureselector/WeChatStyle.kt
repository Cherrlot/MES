package com.zhizhunbao.lib.common.pictureselector

import androidx.core.content.ContextCompat
import com.luck.picture.lib.style.BottomNavBarStyle
import com.luck.picture.lib.style.PictureSelectorStyle
import com.luck.picture.lib.style.SelectMainStyle
import com.luck.picture.lib.style.TitleBarStyle
import com.luck.picture.lib.utils.DensityUtil
import com.luck.picture.lib.R
import com.zhizhunbao.lib.common.CommonApplication
import com.zhizhunbao.lib.common.tool.color
import com.zhizhunbao.lib.common.tool.string


class WeChatStyle : PictureSelectorStyle() {

    init {
        init()
    }

    private fun init() {
        // 主体风格
        val numberSelectMainStyle = SelectMainStyle()
        numberSelectMainStyle.isSelectNumberStyle = true
        numberSelectMainStyle.isPreviewSelectNumberStyle = false
        numberSelectMainStyle.isPreviewDisplaySelectGallery = true
        numberSelectMainStyle.selectBackground = R.drawable.ps_default_num_selector
        numberSelectMainStyle.previewSelectBackground =
            R.drawable.ps_preview_checkbox_selector
        numberSelectMainStyle.selectNormalBackgroundResources =
            R.drawable.ps_select_complete_normal_bg
        numberSelectMainStyle.selectNormalTextColor = R.color.ps_color_53575e.color
        numberSelectMainStyle.selectNormalText = R.string.ps_send.string
        numberSelectMainStyle.adapterPreviewGalleryBackgroundResource =
            R.drawable.ps_preview_gallery_bg
        numberSelectMainStyle.adapterPreviewGalleryItemSize =
            DensityUtil.dip2px(CommonApplication.instance, 52f)
        numberSelectMainStyle.previewSelectText =
            CommonApplication.instance.getString(R.string.ps_select)
        numberSelectMainStyle.previewSelectTextSize = 14
        numberSelectMainStyle.previewSelectTextColor =
            ContextCompat.getColor(CommonApplication.instance, R.color.ps_color_white)
        numberSelectMainStyle.previewSelectMarginRight =
            DensityUtil.dip2px(CommonApplication.instance, 6f)
        numberSelectMainStyle.selectBackgroundResources = R.drawable.ps_select_complete_bg
        numberSelectMainStyle.selectText =
            CommonApplication.instance.getString(R.string.ps_send_num)
        numberSelectMainStyle.selectTextColor =
            ContextCompat.getColor(CommonApplication.instance, R.color.ps_color_white)
        numberSelectMainStyle.mainListBackgroundColor =
            ContextCompat.getColor(CommonApplication.instance, R.color.ps_color_black)
        numberSelectMainStyle.isCompleteSelectRelativeTop = true
        numberSelectMainStyle.isPreviewSelectRelativeBottom = true
        numberSelectMainStyle.isAdapterItemIncludeEdge = false

        // 头部TitleBar 风格
        val numberTitleBarStyle = TitleBarStyle()
        numberTitleBarStyle.isHideCancelButton = true
        numberTitleBarStyle.isAlbumTitleRelativeLeft = true
        numberTitleBarStyle.titleAlbumBackgroundResource = R.drawable.ps_album_bg
        numberTitleBarStyle.titleDrawableRightResource = R.drawable.ps_ic_grey_arrow
        numberTitleBarStyle.previewTitleLeftBackResource = R.drawable.ps_ic_normal_back

        // 底部NavBar 风格
        val numberBottomNavBarStyle = BottomNavBarStyle()
        numberBottomNavBarStyle.bottomPreviewNarBarBackgroundColor =
            ContextCompat.getColor(CommonApplication.instance, R.color.ps_color_half_grey)
        numberBottomNavBarStyle.bottomPreviewNormalText =
            CommonApplication.instance.getString(R.string.ps_preview)
        numberBottomNavBarStyle.bottomPreviewNormalTextColor =
            ContextCompat.getColor(CommonApplication.instance, R.color.ps_color_9b)
        numberBottomNavBarStyle.bottomPreviewNormalTextSize = 16
        numberBottomNavBarStyle.isCompleteCountTips = false
        numberBottomNavBarStyle.bottomPreviewSelectText =
            CommonApplication.instance.getString(R.string.ps_preview_num)
        numberBottomNavBarStyle.bottomPreviewSelectTextColor =
            ContextCompat.getColor(CommonApplication.instance, R.color.ps_color_white)

        titleBarStyle = numberTitleBarStyle
        bottomBarStyle = numberBottomNavBarStyle
        selectMainStyle = numberSelectMainStyle
    }

    companion object {
        private var instance: WeChatStyle? = null

        fun createWeChatStyle(): WeChatStyle? {
            if (null == instance) {
                synchronized(WeChatStyle::class.java) {
                    if (null == instance) {
                        instance = WeChatStyle()
                    }
                }
            }
            return instance
        }

    }
}