package com.zhizhunbao.lib.common.router

/**
 * 路由相关
 */

/** 路由组 - APP */
const val ROUTER_GROUP_APP = "/app"

/** 路由组 - login */
const val ROUTER_GROUP_LOGIN = "/login"

/** 路由组 - limit */
const val ROUTER_GROUP_LIMIT = "/limit"

/** 路由组 - common */
const val ROUTER_GROUP_COMMON = "/common"

/** 路由组 - papers */
const val ROUTER_GROUP_PAPERS = "/papers"

/** 路由组 - selfCheck */
const val ROUTER_GROUP_SELF_CHECK = "/selfCheck"

/** 路由组 - gpsdk */
const val ROUTER_GROUP_GPSDK = "/gpsdk"

/** 路由组 - retention  record*/
const val ROUTER_GROUP_RECORD = "/record"

/** 路由组 - purchase*/
const val ROUTER_GROUP_PURCHASE = "/purchase"

/** 路由组 - setting */
const val ROUTER_GROUP_SETTING = "/setting"

/** 路由组 - reserve */
const val ROUTER_GROUP_RESERVE = "/reserve"

/** 路由组 - home */
const val ROUTER_GROUP_HOME = "/home"

/** 路由组 - company */
const val ROUTER_GROUP_COMPANY = "/company"

/** 主界面 MainActivity */
const val ROUTER_PATH_MAIN = "$ROUTER_GROUP_APP/MainActivity"

/** 网页 WebViewActivity */
const val ROUTER_PATH_WEB = "$ROUTER_GROUP_COMMON/WebViewActivity"

/** 登录 LoginActivity */
const val ROUTER_PATH_LOGIN = "$ROUTER_GROUP_LOGIN/LoginActivity"

/** 主界面 看板列表 BoardFragment*/
const val ROUTER_PATH_BOARD_ACTIVITY = "$ROUTER_GROUP_HOME/BoardFragment"

/** 蓝牙打印机 */
const val ROUTER_PATH_GPSDK = "$ROUTER_GROUP_GPSDK/GpsdkMainActivity"

/** 主界面 菜品类型管理 DishTypeActivity*/
const val ROUTER_PATH_DISH_TYPE_ACTIVITY = "$ROUTER_GROUP_HOME/DishTypeActivity"

/** 预定设置 ReserveActivity */
const val ROUTER_PATH_RESERVE_ACTIVITY = "$ROUTER_GROUP_RESERVE/ReserveActivity"

/** 选择菜品 ReserveSelectDishActivity */
const val ROUTER_PATH_RESERVE_SELECT_DISH_ACTIVITY =
    "$ROUTER_GROUP_RESERVE/ReserveSelectDishActivity"

/** 限额 LimitActivity */
const val ROUTER_PATH_LIMIT_ACTIVITY = "$ROUTER_GROUP_LIMIT/LimitActivity"

/** 菜单预设 ReserveSetActivity */
const val ROUTER_PATH_RESERVE_SET_ACTIVITY = "$ROUTER_GROUP_LIMIT/ReserveSetActivity"

/** 库存管理 StockActivity */
const val ROUTER_PATH_STOCK_ACTIVITY = "$ROUTER_GROUP_PURCHASE/StockActivity"

/** 备货管理 PrepareOrderActivity */
const val ROUTER_PATH_PREPARE_ACTIVITY = "$ROUTER_GROUP_PURCHASE/PrepareOrderActivity"

/** 设置 SettingActivity */
const val ROUTER_PATH_SETTING = "$ROUTER_GROUP_SETTING/SettingActivity"

/** 消息中心 MessageListActivity */
const val ROUTER_PATH_MSG = "$ROUTER_GROUP_SETTING/MessageListActivity"

/** 出库分拣 PutOutActivity */
const val ROUTER_PATH_PUT_OUT = "$ROUTER_GROUP_PURCHASE/PutOutActivity"

/** 入库 PutInActivity */
const val ROUTER_PATH_PUT_IN = "$ROUTER_GROUP_PURCHASE/PutInActivity"

/** 采购管理 PurchaseManagerActivity */
const val ROUTER_PATH_PROVIDER_PURCHASE = "$ROUTER_GROUP_PURCHASE/PurchaseManagerActivity"

/** 下单 OrderActivity */
const val ROUTER_PATH_ORDER = "$ROUTER_GROUP_PURCHASE/OrderActivity"

/** 采购历史 PurchaseHistoryActivity */
const val ROUTER_PATH_PURCHASE_HISTORY = "$ROUTER_GROUP_PURCHASE/PurchaseHistoryActivity"
/** 采购 BuyActivity */
const val ROUTER_PATH_BUY = "$ROUTER_GROUP_PURCHASE/BuyActivity"
/** 地图 MapActivity*/
const val ROUTER_PATH_MAP_ACTIVITY = "$ROUTER_GROUP_COMMON/MapActivity"
/** 公司列表 CompanyListActivity*/
const val ROUTER_PATH_COMPANY_LIST_ACTIVITY = "$ROUTER_GROUP_COMPANY/CompanyListActivity"
/** 供应商管理 SortingListActivity*/
const val ROUTER_PATH_SORTING_LIST_ACTIVITY = "$ROUTER_GROUP_COMPANY/SortingListActivity"
/** 商品编辑 ProductEditActivity*/
const val ROUTER_PATH_PRODUCT_ACTIVITY = "$ROUTER_GROUP_PAPERS/ProductEditActivity"
/** 产品分类管理 ProductTypeManageActivity*/
const val ROUTER_PATH_PRODUCT_TYPE_MANAGE_ACTIVITY = "$ROUTER_GROUP_PAPERS/ProductTypeManageActivity"
/** 每日自检 SelfCheckActivity*/
const val ROUTER_PATH_SELF_CHECK_ACTIVITY = "$ROUTER_GROUP_SELF_CHECK/SelfCheckActivity"
/** 晨检记录 MorningRecordActivity*/
const val ROUTER_PATH_MORNING_ACTIVITY = "$ROUTER_GROUP_SELF_CHECK/MorningRecordActivity"
/** 预警记录 WarnRecordActivity*/
const val ROUTER_PATH_WARN_RECORD_ACTIVITY = "$ROUTER_GROUP_SELF_CHECK/WarnRecordActivity"
/** 消毒记录 DisinfectActivity*/
const val ROUTER_PATH_DISINFECT_RECORD_ACTIVITY = "$ROUTER_GROUP_SELF_CHECK/DisinfectActivity"