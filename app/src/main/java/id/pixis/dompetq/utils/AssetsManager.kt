package id.pixis.dompetq.utils

import android.content.Context
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

object AssetsManager {
    @Throws(IOException::class)
    fun getJson(context: Context, name : String) : JSONArray {
        val inputStream : InputStream = context.assets.open("$name.json")
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer)

        return JSONArray(json)
    }
}