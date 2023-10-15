package com.zhizhunbao.lib.common.bean

/**
 * description:设备相关信息
 */
data class DeviceInfoBean(
    var isZhongkongPay: Boolean? = null,//是否中控支付
    var departmentId: Int? = 0,//单位id
    var departmentName: String? = null,// 单位名称
    var diningId: Long? = 0,//食堂id
    var diningName: String? = null,//餐厅名称
    var dishLibraryId: Long? = 0,//菜品库id
    var deviceId: Long? = 0,//设备id
    var deviceName: String? = null,//设备名称
    var serialNumber: String? = null,//设备编号
    var name: String? = null,//设备名称
    var type: Int? = -1,//设备类型
    var isBinding: String? = null,
    var id: String? = null,
    var faceSN: String? = null,//人脸识别序列号
    var isEnableFace: Boolean? = null,//是否启用人脸
    var isHandoverWork: Boolean? = false,//是否启用交接班
    var isOpenDishIdentify: Boolean? = true,//是否启用智慧结算
    var isStandbyCash: Boolean? = false,// 是否启用备用金
    var operatorName: String? = "", //操作员姓名
    var operatorUserId: String? = "",//操作者用户id
    var operatorPhone: String? = null,//操作员手机号
    var mvLibraryId: String? = null,//新识别库id
    var isPhoneNumberPay: Boolean? = false,//是否开启手机号支付
    var isBalanceMixedPay: Boolean? = false//是否支开启混合支付
)