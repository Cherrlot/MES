package com.zhizhunbao.lib.common.net.constant

const val AUTHORIZATION_HEADER = "Authorization"

/**
 * 服务端口
 */
const val API_PORT = ":7000"

/**
 * 服务端上传口
 */
const val UPLOAD_PORT = ":8001"

/**
 * 本地部署接口
 */
const val LOCAL_BASE = ""
//const val LOCAL_BASE = "/api/web/v2/"

/**
 * 超时时间
 */
const val TIME_OUT: Long = 20

/**
 * 登录
 */
const val LOGIN_URL: String = "login/"

/**
 * 获取看板数据
 */
const val BOARD_URL: String = "api/orders"

/**
 * 获取操作数据
 */
const val OPTION_URL: String = "api/tasks"

/**
 * 扫码
 */
const val SCAN_QRCODE_URL: String = "api/state/picking/materials"

/**
 * 极光推送
 */
const val JPUSH_URL: String = "api/sys-users"

/**
 * 退出登录
 */
const val LOGOUT_URL = "root/token/delete"

/**
 * app版本检查
 */
const val CHECK_UPDATE_APP_URL = "api/sys-options"

/** ----------------------------本地菜品接口开始----------------------------------------**/

/**
 * 获取餐厅信息
 */
const val DINING_INFO_URL: String = "dining_room/find"

/**
 * 菜品数据
 */
const val DISH_LIST_URL = "root/dish/item/self/list"
/**
 * 一键上下架菜品
 */
const val DISH_PUT_AWAY_URL = "root/dish/item/upOrDown/update"
/**
 * 图片上传接口
 */
const val UPLOAD_PIC_URL = "/upload/images/origin"
/**
 * 删除菜品
 */
const val DISH_REMOVE_URL = "root/dish/item/self/delete"

/**
 * 创建菜品
 */
const val DISH_CREATE_URL = "root/dish/item/self/create"

/**
 * 修改菜品
 */
const val DISH_MODIFY_URL = "root/dish/item/self/update"

/**
 * 菜品分类管理列表
 */
const val DISH_TYPE_MANAGER_URL = "root/dish/item/type/list"

/**
 * 获取标准菜
 */
const val DISH_STANDARD_NAME_URL = "root/library/dish/list"

/**
 * 菜品分类管理排序
 */
const val SORT_DISH_TYPE_URL = "root/dish/item/typeIndex/update "

/**
 * 删除菜品分类
 */
const val DELETE_DISH_TYPE_URL = "root/dish/item/type/delete"

/**
 * 创建菜品分类
 */
const val CREATE_DISH_TYPE_URL = "root/dish/item/type/create"

/**
 * 修改菜品分类
 */
const val MODIFY_DISH_TYPE_URL = "root/dish/item/type/update"

/**
 * 修改菜品排序
 */
const val MODIFY_DISH_SORT_URL = "item/orderIndex"
/**
 * 识别
 */
const val RECOGNIZE_URL: String = "root/dish/recg/create"
/**
 * 识别
 */
const val UPLOAD_URL: String = "/upload/images/spu"
/** ----------------------------本地菜品接口结束----------------------------------------**/


/** ----------------------------设置接口开始----------------------------------------**/
/**
 * 获取用户信息
 */
const val ACCOUNT_URL = "root/user/self/get"
/**
 * 添加餐厅
 */
const val DINING_ADD_URL = "root/dining/self/create"
/**
 * 编辑餐厅
 */
const val DINING_MODIFY_URL = "root/dining/self/update"
/**
 * 删除餐厅
 */
const val DINING_DELETE_URL = "root/dining/self/delete"
/**
 * 餐厅启用
 */
const val DINING_ENABLE_URL = "root/dining/self/update"
/**
 * 餐厅停用
 */
const val DINING_DISABLE_URL = "root/dining/self/update"
/**
 * 用户列表
 */
const val USER_LIST_URL = "root/user/self/list"
/**
 * 用户修改
 */
const val USER_MODIFY_URL = "root/user/self/update"
/**
 * 用户删除
 */
const val USER_DELETE_URL = "root/user/self/delete"
/**
 * 重置用户密码
 */
const val USER_RESET_PWD_URL = "root/user/password/update"
/**
 * 用户创建一
 */
const val USER_CREATE_URL = "root/user/self/create"

/**
 * 修改密码
 */
const val EDIT_PWD_URL = "api/resetpwd"

/**
 * 获取人员列表
 */
const val ROLE_LIST_URL = "root/mall/employee/list"

/**
 * 获取设备列表
 */
const val DEVICE_LIST_URL = "api/bas-machines"

/**
 * 获取人员详情
 */
const val ROLE_DETAIL_URL = "root/mall/employee/get"

/**
 * 获取角色列表
 */
const val ROLE_TYPE_LIST_URL = "root/mall/role/list"

/**
 * 获取单位列表
 */
const val UNIT_LIST_URL = "unit/list"

/**
 * 新增人员
 */
const val STAFF_ADD_URL = "root/mall/employee/create"

/**
 * 删除人员
 */
const val STAFF_DELETE_URL = "root/mall/employee/delete"

/**
 * 修改人员
 */
const val STAFF_MODIFY_URL = "root/mall/employee/update"

/**
 * 新增单位
 */
const val UNIT_ADD_URL = "unit/create"

/**
 * 删除单位
 */
const val UNIT_DELETE_URL = "unit/delete"

/**
 * 修改单位
 */
const val UNIT_MODIFY_URL = "unit/modify"

/**
 * 获取供应商列表
 */
const val PROVIDER_LIST_URL = "root/mall/buyer/get"

/**
 * 新增供应商
 */
const val PROVIDER_ADD_URL = "root/procurement/provider/create"

/**
 * 删除供应商
 */
const val PROVIDER_DELETE_URL = "root/procurement/provider/delete"

/**
 * 修改供应商
 */
const val PROVIDER_MODIFY_URL = "root/procurement/provider/update"
/** ----------------------------设置接口结束----------------------------------------**/

/** ----------------------------消费限额接口开始----------------------------------------**/
/**
 * 消费限额创建
 */
const val LIMIT_CREATE_URL = "root/dining/mealTimes/create"
/**
 * 消费限额列表查询
 */
const val LIMIT_LIST_URL = "root/dining/self/get"
/**
 * 消费限额删除
 */
const val LIMIT_DELETE_URL = "root/dining/mealTimes/delete"
/**
 * 消费限额编辑
 */
const val LIMIT_MODIFY_URL = "root/dining/mealTimes/update"
/** ----------------------------消费限额接口结束----------------------------------------**/


/** ----------------------------菜单预定接口开始----------------------------------------**/
/**
 * 获取菜单预定列表
 */
const val RESERVE_LIST_URL = "root/dish/item/menu/list"
/**
 * 获取菜单预定详情
 */
const val RESERVE_DETAIL_URL = "root/dish/item/menu/get"
/**
 * 菜单预定创建
 */
const val RESERVE_CREATE_URL = "root/dish/item/menu/create"
/**
 * 菜单预定编辑
 */
const val RESERVE_UPDATE_URL = "root/dish/item/menu/update"
/** ----------------------------菜单预定接口结束----------------------------------------**/

/** ----------------------------菜单留样接口开始----------------------------------------**/

/**
 * 菜单留样
 */
const val RETENTION_URL = "root/order/sample/create"

/**
 * 查询未留样的记录
 */
const val UN_RETENTION_LIST_URL = "root/dish/item/menu/get"

/** ----------------------------菜单留样接口结束----------------------------------------**/

/** ----------------------------留样记录接口开始----------------------------------------**/

/**
 * 菜单留样记录列表
 */
const val RETENTION_LIST_URL = "root/order/sample/list"

/**
 * 菜单留样记录详情
 */
const val RETENTION_DETAIL_URL = "root/order/sample/get"

/**
 * 菜单留样记录详情删除
 */
const val RETENTION_DELETE_URL = "root/order/sample/delete"

/** ----------------------------留样记录接口结束----------------------------------------**/


/** ----------------------------采购管理接口开始----------------------------------------**/

/**
 * 采购管理列表
 */
const val PURCHASE_LIST_URL = "root/procurement/self/list"

/**
 * 采购详情列表
 */
const val PURCHASE_DETAIL_LIST_URL = "root/procurement/self/get"

/**
 * 采购管理添加
 */
const val PURCHASE_ADD_URL = "root/procurement/self/create"

/**
 * 盘库
 */
const val PRODUCT_CHECK_URL = "root/mall/stocking/create"

/**
 * 采购管理编辑
 */
const val PURCHASE_UPDATE_URL = "root/procurement/self/update"

/**
 * 采购管理删除
 */
const val PURCHASE_DELETE_URL = "root/procurement/self/delete"

/**
 * 采购数据自动生成
 */
const val PURCHASE_AUTO_CREATE_URL = "root/procurement/ingredients/bymenu"

/**
 * 获取商品列表
 */
const val PURCHASE_MATERIAL_URL = "root/mall/sku/list"

/**
 * 获取商品详情
 */
const val PRODUCT_DETAIL_URL = "root/mall/spu/get"

/**
 * 商品添加
 */
const val PRODUCT_CREATE_URL = "root/mall/spu/create"

/**
 * 商品修改
 */
const val PRODUCT_UPDATE_URL = "root/mall/spu/update"

/**
 * 商品删除
 */
const val PRODUCT_DELETE_URL = "root/mall/spu/delete"

/**
 * 拍照识别原材料
 */
const val PURCHASE_MATERIAL_RECOGNIZE_URL = "root/dish/recg/ingredient"

/**
 * 获取供应商列表
 */
const val SORTING_LIST_URL = "root/mall/vendor/list"

/**
 * 获取类型列表
 */
const val TYPE_LIST_URL = "root/mall/category/list"

/**
 * 获取类型添加
 */
const val TYPE_LIST_CREATE_URL = "root/mall/category/create"

/**
 * 获取类型编辑
 */
const val TYPE_LIST_UPDATE_URL = "root/mall/category/update"

/**
 * 获取类型删除
 */
const val TYPE_LIST_DELETE_URL = "root/mall/category/delete"

/**
 * 获取商品列表
 */
const val PRODUCT_LIST_URL = "root/mall/spu/list"

/**
 * 获取公司列表
 */
const val CUSTOMER_LIST_URL = "root/mall/customer/list"

/**
 * 获取公司详情
 */
const val CUSTOMER_DETAIL_URL = "root/mall/customer/get"

/**
 * 创建供应商
 */
const val SORTING_CREATE_URL = "root/mall/vendor/create"

/**
 * 编辑供应商
 */
const val SORTING_UPDATE_URL = "root/mall/vendor/update"

/**
 * 创建公司
 */
const val CUSTOMER_CREATE_URL = "root/mall/customer/create"

/**
 * 编辑公司
 */
const val CUSTOMER_UPDATE_URL = "root/mall/customer/update"

/**
 * 删除供应商
 */
const val SORTING_DELETE_URL = "root/mall/vendor/delete"

/**
 * 删除公司
 */
const val CUSTOMER_DELETE_URL = "root/mall/customer/delete"

/**
 * 代客下单
 */
const val ORDER_CREATE_URL = "root/mall/order/create"

/**
 * 查地址
 */
const val ADDRESS_LIST_URL = "root/mall/customer/get"

/**
 * 查采购列表
 */
const val MALL_PURCHASE_LIST_URL = "root/mall/purchase/list"

/**
 * 采购列表新增
 */
const val MALL_PURCHASE_CREATE_URL = "root/mall/purchase/create"

/**
 * 采购完成
 */
const val MALL_BUY_URL = "root/mall/status/update"

/**
 * 采购列表更新
 */
const val MALL_PURCHASE_UPDATE_URL = "root/mall/purchase/update"

/**
 * 订单列表
 */
const val MALL_ORDER_LIST_URL = "root/mall/order/list"

/**
 * 订单详情
 */
const val MALL_ORDER_DETAIL_URL = "root/mall/order/get"

/**
 * 分拣
 */
const val MALL_ORDER_UPDATE_URL = "root/mall/picking/update"

/**
 * 确认备货
 */
const val MALL_PREPARE_URL = "root/mall/status/update"

/** ----------------------------采购管理接口结束----------------------------------------**/

/** ----------------------------自查通知接口开始----------------------------------------**/

/**
 * 消息列表
 */
const val SELF_NOTIFY_LIST_URL = "root/mall/notice/list"

/**
 * 消息全部已读
 */
const val SELF_NOTIFY_READ_URL = "root/mall/notice/update"

/**
 * 自查通知数量
 */
const val SELF_NOTIFY_COUNT_URL = "supv/record/countSelf"

/**
 * 预警处理
 */
const val WARNING_HANDLE_URL = "root/supv/record/status"

/**
 * 未留样明细列表
 */
const val UN_LEAVE_SAMPLE_URL = "root/dish/item/menu/get"

/**
 * 采购异常明细列表
 */
const val UN_PURCHASE_URL = "supv/record/listUnPurchase"

/**
 * 菜单明细列表
 */
const val UN_MENU_URL = "root/dish/item/menu/list"
/** ----------------------------自查通知接口结束----------------------------------------**/


/** ----------------------------营业执照接口开始----------------------------------------**/

/**
 * 营业执照查询
 */
const val BUSINESS_LICENSE_URL = "root/dining/self/get"

/**
 * 营业执照新增
 */
const val BUSINESS_LICENSE_SAVE_URL = "root/dining/licence/create"

/**
 * 营业执照修改
 */
const val BUSINESS_LICENSE_UPDATE_URL = "root/dining/licence/update"
/** ----------------------------营业执照接口结束----------------------------------------**/

/** ----------------------------健康证接口开始----------------------------------------**/

/**
 * 健康证查询
 */
const val HEALTH_URL = "root/dining/self/get"

/**
 * 健康证新增
 */
const val HEALTH_CREATE_URL = "root/dining/healthCerts/create"

/**
 * 健康证修改
 */
const val HEALTH_MODIFY_URL = "root/dining/healthCerts/update"

/**
 * 健康证删除
 */
const val HEALTH_DELETE_URL = "root/dining/healthCerts/delete"
/** ----------------------------健康证接口结束----------------------------------------**/

/** ----------------------------每日自检接口开始----------------------------------------**/

/**
 * 每日自检日期查询
 */
const val SELF_CHECK_DATE_URL = "supv/record/listInspectionDate"

/**
 * 每日自检当天数据查询
 */
const val SELF_CHECK_INFO_URL = "root/supv/record/get"

/**
 * 营业执照新增/修改
 */
const val SELF_CHECK_SAVE_URL = "root/supv/record/create"
/** ----------------------------每日自检接口结束----------------------------------------**/