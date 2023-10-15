package com.zhizhunbao.lib.common.util

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.log.AppLog
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class Util {
    companion object {
        /**
         * 获取支付类型
         */
        fun getPaymentType(paymentType: Int?) = when (paymentType.safe()) {
            1 -> "会员账户"
            2 -> "支付宝"
            3 -> "微信"
            4 -> "云闪付"
            5 -> "现金"
            else -> "其他"
        }

        fun setMenuItemTextColor(menuItem: MenuItem, color: Int) {
            val text = menuItem.title
            val builder = SpannableStringBuilder(text)
            builder.setSpan(
                ForegroundColorSpan(color),
                0,
                text?.length.safe(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            menuItem.title = builder
        }

        /**
         * json转换map
         * @param jsonStr
         * @return
         */
        private fun getMapForJson(jsonStr: String): Map<String, Any> {
            val jsonObject: JSONObject
            try {
                jsonObject = JSONObject(jsonStr)
                val keyIter = jsonObject.keys()
                var key: String
                var value: Any
                val valueMap: MutableMap<String, Any> = HashMap()
                while (keyIter.hasNext()) {
                    key = keyIter.next()
                    value = jsonObject[key]
                    valueMap[key] = value
                }
                return valueMap
            } catch (e: Exception) {
                e.printStackTrace()
                AppLog.e(e.toString())
            }
            return mutableMapOf()
        }

        /**
         * json转换list<map>
         * @param jsonStr
         * @return
        </map> */
        fun getListForJson(jsonStr: String?): List<Map<String, Any>> {
            val list: MutableList<Map<String, Any>> = mutableListOf()
            try {
                val jsonArray = JSONArray(jsonStr)
                var jsonObj: JSONObject
                for (i in 0 until jsonArray.length()) {
                    jsonObj = jsonArray[i] as JSONObject
                    list.add(getMapForJson(jsonObj.toString()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                AppLog.e(e.toString())
            }
            return list
        }

        fun getJson(context: Context, fileName: String): String {
            val stringBuilder = StringBuilder()
            try {
                val assetManager = context.assets
                val bf = BufferedReader(
                    InputStreamReader(
                        assetManager.open(fileName)
                    )
                )
                var line: String?
                while (bf.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                AppLog.e(e)
            }
            return stringBuilder.toString()
        }
    }
}