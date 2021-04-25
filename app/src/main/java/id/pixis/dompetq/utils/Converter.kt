package id.pixis.dompetq.utils

import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Converter {
    @Throws(ParseException::class)
    fun dateFormat(date: String, input: String, output: String) : String{
        var format = SimpleDateFormat(input, Locale.getDefault())

        val newDate: Date? = format.parse(date)

        format = SimpleDateFormat(output, Locale.getDefault())

        return format.format(newDate!!)
    }

    fun currencyIdr(total: Int): String? {
        val localeID = Locale("in", "ID")
        val format: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
        return format.format(total)
    }

}