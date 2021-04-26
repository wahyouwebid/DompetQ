package id.pixis.dompetq.utils

import java.util.*


/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */
object Utils {

    fun getFirstDate() : String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return Converter.dateFormat(calendar.time, "yyyyMMdd")
    }

    fun getLastDate() : String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return Converter.dateFormat(calendar.time, "yyyyMMdd")
    }
}