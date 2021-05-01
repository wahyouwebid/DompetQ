package id.pixis.dompetq.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import id.pixis.dompetq.databinding.ActivitySplashBinding
import id.pixis.dompetq.ui.main.MainActivity
import id.pixis.dompetq.ui.service.CategoriesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val binding : ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        moveMainActivity()
        CoroutineScope(Dispatchers.Main).launch {
            startService(Intent(this@SplashActivity, CategoriesService::class.java))
        }
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun moveMainActivity() {
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}