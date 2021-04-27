package id.pixis.dompetq.utils

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */
object Extentions {
    fun JSONArray.foreach(callback: (data: JSONObject) -> Unit) {
        for(position in 0 until this.length()){
            callback.invoke(this.getJSONObject(position))
        }
    }
}