package com.zhizhunbao.lib.common.util

import com.zhizhunbao.lib.common.ext.safe
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by xwh on 2020/4/2  星期四
 * description:
 */
class TimeUtils {

    companion object {

        /**
         * 时间选择器：时分
         */
        var mTimePickerHMType = booleanArrayOf(false, false, false, true, true, false)

        /**
         * 时间选择器：年月日
         */
        var mTimePickerYMDType = booleanArrayOf(true, true, true, false, false, false)

        /**
         * 时间选择器：年月日时分
         */
        var mTimePickerAllType = booleanArrayOf(true, true, true, true, true, false)

        const val DATE_FORMAT_1 = "yyyy年MM月dd日"
        const val DATE_FORMAT_2 = "yyyy-MM-dd"
        const val DATE_FORMAT_3 = "yyyy/MM/dd"
        const val DATE_FORMAT_4 = "yyyy-MM-dd HH:mm:ss"
        const val DATA_FORMAT_5 = "MM月dd日"
        const val DATA_FORMAT_6 = "yy/MM/dd"
        const val DATE_FORMAT_7 = "yyyy/MM/dd HH:mm:ss"
        const val DATE_FORMAT_8 = "HH:mm:ss"
        const val DATE_FORMAT_9 = "HH:mm"
        const val DATE_FORMAT_10 = "yyyy-MM-dd HH:mm"
        const val DATE_FORMAT_11 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'+08:00'"
        const val DATE_FORMAT_12 = "yyyy-MM-dd'T'HH:mm:SS"

        /**
         * 获取Calendar
         */
        fun getFormatCalendar(time: String, format: String) : Calendar{
            val s = Calendar.getInstance()
            try {
                val df = SimpleDateFormat(format, Locale.CHINA)
                val dt = df.parse(time)
                s.time = dt?: Date()
            } catch (e: Exception) {
                return s
            }
            return s
        }

        /**
         * 获取本月第一天
         */
        fun getMonthFirstDay(format: String? = DATE_FORMAT_2): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, 0)
            calendar[Calendar.DAY_OF_MONTH] = 1
            val df2 = SimpleDateFormat(format, Locale.CHINA)
            return df2.format(calendar.time)
        }

        /**
         * 获取本月最后一天
         */
        fun getMonthLastDay(format: String? = DATE_FORMAT_2): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, 1)
            calendar[Calendar.DAY_OF_MONTH] = 0
            val df2 = SimpleDateFormat(format, Locale.CHINA)
            return df2.format(calendar.time)
        }

        fun getCreateDate(CreatedAt: String?, format: String? = DATE_FORMAT_4): String{
            var s = CreatedAt
            try {
                val df = SimpleDateFormat(DATE_FORMAT_12, Locale.CHINA)
                val dt = df.parse(s.safe())
                val df2 = SimpleDateFormat(format, Locale.CHINA)
                s = df2.format(dt?: Date())
            } catch (e: Exception) {
                return s.safe()
            }
            return s.safe()
        }
        /**
         * @param time 2016/9/10
         * @return 返回 yyyy年MM月dd日
         */
        fun changeDateFormat(currentFormat: String, changeDefault: String, time: String): String {
            return try {
                val date = SimpleDateFormat(currentFormat, Locale.getDefault()).parse(time)
                SimpleDateFormat(changeDefault, Locale.getDefault()).format(date?: Date())
            } catch (e: Exception) {
                ""
            }

        }


        /**
         * @param format      格式样式
         * @param timeMillins 时间毫秒
         */
        fun getFormatDateToString(format: String, timeMillins: Long): String {
            return try {
                SimpleDateFormat(format, Locale.getDefault()).format(Date(timeMillins))
            } catch (e: Exception) {
                ""
            }
        }

        /**
         * 获取当天时间格式化
         */
        fun getStringToday(format: String? = DATE_FORMAT_2): String {
            return try {
                SimpleDateFormat(format, Locale.getDefault()).format(Date())
            } catch (e: Exception) {
                ""
            }
        }

        /**
         *@param format      格式样式
         * @param calendar
         */
        fun getFormatDateToString(format: String, calendar: Calendar): String {
            return try {
                SimpleDateFormat(format, Locale.getDefault()).format(calendar.time)
            } catch (e: Exception) {
                ""
            }
        }

        fun getFormatDateToString(format: String, date: Date): String {
            return try {
                SimpleDateFormat(format, Locale.getDefault()).format(date)
            } catch (e: Exception) {
                ""
            }
        }


    }
}