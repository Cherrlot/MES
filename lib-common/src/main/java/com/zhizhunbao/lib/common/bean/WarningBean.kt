package com.zhizhunbao.lib.common.bean

import android.os.Parcelable
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.util.TimeUtils
import kotlinx.parcelize.Parcelize

/**
 * 预警
 */
@Parcelize
data class WarningBean(
    var id: String?,
    var cubeDiningId: String?,
    var dining_id: String?,
    var companyId: String?,
    var company_name: String?,
    var dining_name: String?,
    var userId: String?,
    /**
     * enum AbnormalEnum {
    NONE =0;
    KITCH =1;    //后厨
    MORNING=2;   //晨检
    DISINFECTION=3; //消毒
    MENU =4;    //菜单
    SAMPLE =5;   //留样
    PROCUREMENT =6; //采购
    CONSUME =7;   //消耗
    WATER =8 ;   //水质
    INSPECTION =9; //巡查
    LICENSE=10;   //证照
    EXECUTION=11;  //执法
    }
     */
    var kind: Int?,
    /**
     * 1: 自查； 2; 自查处理 3 ;自查不处理 4： 核查有问题； 5： 核查没问题，8; 确认 9; 问题存档 99：不处理
     */
    var status: Int?,
    var updated_at: String?,
    var remark: String?,
    var reply: String?,
    var notify: String?,
    var payLimitName: String?,
    var payLimitId: String?,
) : Parcelable {

    fun getDate() = TimeUtils.changeDateFormat(
        TimeUtils.DATE_FORMAT_11,
        TimeUtils.DATE_FORMAT_2,
        updated_at.safe()
    )

    fun isHandle() = status == 2 || status == 3

    fun isCheck() = status.safe(3) != 3

    fun getTitle(): String {
        return when (kind.safe(1)) {
            2 -> "晨检预警"
            3 -> "消毒预警"
            4 -> "菜单预警"
            5 -> "留样预警"
            6 -> "采购预警"
            7 -> "消耗预警"
            8 -> "水质预警"
            9 -> "巡查预警"
            10 -> "证照预警"
            11 -> "执法预警"
            else -> "后厨预警"
        }
    }

    fun getNoData(): String {
        return remark.safe()
    }

    fun getWarnDetailTitle(): String {
        return remark.safe()
    }

    fun getReason(): String {
        return remark.safe()
    }

    fun getHandled(): String {
        return if (status == 2 || status == 3) "已自查" else "待自查"
    }
}