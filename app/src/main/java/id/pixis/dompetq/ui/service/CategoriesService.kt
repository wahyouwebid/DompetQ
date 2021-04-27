package id.pixis.dompetq.ui.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.data.entity.Categories
import id.pixis.dompetq.repository.Repository
import id.pixis.dompetq.utils.AssetsManager
import id.pixis.dompetq.utils.Extentions.foreach
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesService : Service() {

    @Inject
    lateinit var repository : Repository

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        val json = AssetsManager.getJson(this, "categories")
        CoroutineScope(Dispatchers.IO).launch {
            json.foreach { row ->
                row.getJSONArray("categories").foreach {
                    repository.addCategories(
                        Categories(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("icon"),
                            it.getInt("type")
                        )
                    )
                }
            }

            withContext(Dispatchers.Main){
                destroyService()
            }
        }
    }

    private fun destroyService(){
        stopSelf()
    }
}