package com.zhizhunbao.lib.common.util

/**
 * description:支付方式utils
 */
class PayWayUtils {

    companion object {

        fun getPaymentString(paymentType: Int): String {
            return when (paymentType) {
                0 -> "会员支付"
                2 -> "会员支付(刷卡)"
                3 -> "会员支付(刷脸)"
                5 -> "现金支付"
                6 -> "微信支付"
                7 -> "支付宝支付"
                8 -> "京东支付"
                9 -> "云闪付支付"
                10 -> "市民卡支付"
                11 -> "混合支付"
                else -> "其他支付"
            }
        }
    }
}