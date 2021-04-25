package id.pixis.dompetq.ui.bill.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.databinding.ActivityAddBillBinding
import id.pixis.dompetq.ui.bill.BillViewModel

@AndroidEntryPoint
class AddBillActivity : AppCompatActivity() {

    private val binding : ActivityAddBillBinding by lazy {
        ActivityAddBillBinding.inflate(layoutInflater)
    }

    private val viewModel : BillViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        setupListener()
    }

    private fun setupListener(){
        with(binding){
            imgBack.setOnClickListener { finish() }
            btnSave.setOnClickListener {
                val data = Bill(
                    null,
                    etName.text.toString(),
                    etAmount.text.toString().toInt(),
                    "10 jan 2020",
                    "https://cdn1.iconfinder.com/data/icons/e-commerce-and-shopping-line-1/64/01-Bill-512.png",
                    etCategory.text.toString(),
                    "Notes"
                )

                saveData(data)
            }
        }
    }

    private fun saveData(data : Bill){
        viewModel.saveData(data)
        finish()
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}