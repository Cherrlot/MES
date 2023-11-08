package com.zhizhunbao.lib.common.mmkv

import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.bean.AccountBean
import com.zhizhunbao.lib.common.bean.CompanyInfoBean
import com.zhizhunbao.lib.common.bean.DiningListBean
import com.zhizhunbao.lib.common.enum.TacticsEnum

object AppLocalData {
    /** 是否初次启动 */
    var isFirstStart by MMKVUtil(true)

    /** 是否初次启动 */
    var enableTorch by MMKVUtil(false)

    /** 视频流触发识别餐盘 */
    var autoAnalyzer by MMKVUtil(true)

    /** 餐盘识别率 */
    var confidencePercent by MMKVUtil(0.8f)

    /** 极光推送id */
    var jPushId by MMKVUtil("")

    /** 全局token */
    var token by MMKVUtil("")

    /** 登录用户id */
    var authId by MMKVUtil("")

    /** 登录用户id */
    var userId by MMKVUtil("")

    /** 公司id */
    var companyId by MMKVUtil("")

    /** 本地接口ip */
    var localIp by MMKVUtil(BuildConfig.BASE_URL)

    /** 餐厅信息 */
    var diningInfo: DiningListBean? by MMKVUtil(DiningListBean())

    /** 登录用户信息 */
    var accountInfo: AccountBean? by MMKVUtil(AccountBean())

    /** 公司信息 */
    var companyInfo by MMKVUtil(CompanyInfoBean())

    /** 登录账号 */
    var userName by MMKVUtil("")

    /** 机器编号 */
    var machineNo by MMKVUtil("")

    /** 车间属性 */
    var workplace by MMKVUtil("")

    /** 公司名称 */
    var companyName by MMKVUtil("")

    /** 手机ip */
    var serverIp by MMKVUtil("192.168.100.123")

    /** 是否是离线模式 */
    var isOfflineMode by MMKVUtil(false)

    /** 相片识别策略 */
    var discernTactics by MMKVUtil(TacticsEnum.SINGLE.tactics)

    /**
     * 重置所有状态
     */
    fun resetAll() {
        token = ""
        authId = ""
        machineNo = ""
        localIp = BuildConfig.BASE_URL
        diningInfo = DiningListBean()
        companyInfo = CompanyInfoBean()
    }
}