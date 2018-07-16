package com.example.sho.myportalapp.util

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * TODO クラス説明
 *
 *
 * Created by sho on 2018/04/04.
 */
class CalenderUtilTest{

    val cu = CalenderUtil()
    val targetDateStr = "2018-04-05 00:00:00"
    var targetDate: Date? = null

    @Before
    fun getTargetDate() {
        targetDate = cu.parseToSimpleDataFormat(targetDateStr)
    }

}