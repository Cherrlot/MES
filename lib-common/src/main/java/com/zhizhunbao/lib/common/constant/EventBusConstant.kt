package com.zhizhunbao.lib.common.constant



/** 通知更改餐厅 */
const val BUS_CHANGE_DINING = "change_dining"

/** 通知检查菜品分类是否更改 */
const val BUS_REFRESH_OPTION = "refresh_option"

/** 通知预定菜单界面刷新数据 */
const val BUS_REFRESH_WORK_ORDER = "refresh_work_order"

/** 通知预定菜单保存接口界面刷新数据 */
const val BUS_WEBSOCKET_CLOSE    = "websocket_close"
/** 通知限额界面刷新数据 */
const val BUS_REFRESH_LIMIT_MANAGE = "refresh_limit_manage"
/** 用户端连接状态socket */
const val BUS_REFRESH_CLIENT_CONNECT_SOCKET = "client_connect_socket"
/** 用户端获取数据socket */
const val BUS_REFRESH_CLIENT_DATA_SOCKET = "client_data_socket"
/** 修改相似菜品 */
const val BUS_MODIFY_SIMILAR = "modify_similar"
/** 修改服务端ip */
const val BUS_MODIFY_SERVER_IP = "modify_server_ip"
/** 修改供应商 */
const val BUS_MODIFY_PROVIDER = "modify_provider"
/** 修改采购员 */
const val BUS_MODIFY_SORTING = "modify_sorting"
/** 修改单位 */
const val BUS_MODIFY_COMPANY = "modify_company"
/** 修改采购信息 */
const val BUS_MODIFY_PURCHASE = "modify_purchase"
/** 修改公司地址 */
const val BUS_MODIFY_ADDRESS = "modify_address"
/** 修改公司账户 */
const val BUS_MODIFY_ACCOUNT = "modify_account"
/** 采购原材料信息 */
const val BUS_MODIFY_PURCHASE_MATERIAL = "modify_purchase_material"
/** 分拣订单变动 */
const val BUS_MODIFY_PUT_OUT = "modify_put_out"
/** 订单备货 */
const val BUS_MODIFY_ORDER = "modify_order"
/** 盘库 */
const val BUS_MODIFY_PRODUCT = "modify_product"
/** 通知商品类型列表界面刷新数据 */
const val BUS_MODIFY_PRODUCT_TYPE = "modify_product_type"
/** 订单创建成功 */
const val BUS_ORDER_FINISH = "order_finish"

/** 通知角色管理刷新 */
const val BUS_MODIFY_ROLE = "role_refresh"
/** 通知餐厅列表刷新 */
const val BUS_DINING_LIST_REFRESH = "dining_refresh"

/** 选中餐厅地址 */
const val BUS_DINING_ADDRESS = "dining_address"
/** 通知用户列表刷新 */
const val BUS_USER_LIST_REFRESH = "user_refresh"
/** 通知自查列表刷新 */
const val BUS_NOTIFY_LIST_REFRESH = "notify_refresh"
/** 通知自查数量刷新 */
const val BUS_NOTIFY_COUNT_REFRESH = "notify_count_refresh"
/** 通知健康证刷新 */
const val BUS_HEALTH_REFRESH = "health_refresh"
/** 通知每日自检刷新 */
const val BUS_SELF_CHECK_REFRESH = "self_check_refresh"
/** 跳转登录界面 */
const val BUS_LOGIN = "goto_login"