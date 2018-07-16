package com.example.sho.myportalapp.util

import android.util.Log

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

/**
 *
 *
 *
 * Created by sho on 2017/12/26.
 */

class CalenderUtil {

    val baseFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = LocalDateTime.now()
    val milSecFromHour = 1000 * 60 * 60

    fun parseToSimpleDataFormat(time: String): Date? {
        return try {
            val baseDate = baseFormat.parse(time)
            Log.d("DEBUG", date.toString())
            baseDate
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun parseToTZDataFormat(time: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return try {
            val TZdate = format.parse(time)
            Log.d("DEBUG", date.toString())
            TZdate
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun getCurrentDate(): Date = baseFormat.parse(
            date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))

    /**
     * dateStr: 対象の日付のストリング、現在時刻より前の時刻を引数に持つ
     */
    fun calcTimeWithCurrentDate(targetDate: Date): Long {
        if (targetDate.time.compareTo(getCurrentDate().time) == 1) {
            return (getCurrentDate().time - targetDate.time) / milSecFromHour
        }
        return getCurrentDate().time
    }

    /**
     * ミリセカンドからX時間、X日へ変換する
     */
    fun convertHourToDay(sec: Long): Int = ((sec / 24).toInt())


}
