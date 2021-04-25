package id.pixis.dompetq.ui.bill.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import id.pixis.dompetq.R
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.databinding.ActivityAddBillBinding
import id.pixis.dompetq.ui.bill.BillViewModel
import id.pixis.dompetq.utils.Converter.dateFormat
import java.util.*

@AndroidEntryPoint
class AddBillActivity : AppCompatActivity() {

    private val binding : ActivityAddBillBinding by lazy {
        ActivityAddBillBinding.inflate(layoutInflater)
    }

    private val viewModel : BillViewModel by viewModels()

    private lateinit var date : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        setupListener()
    }

    private fun setupListener(){
        with(binding){
            imgBack.setOnClickListener { finish() }
            etDueDate.setOnClickListener { setupDate() }
            btnSave.setOnClickListener {

                if (
                        etName.text.isNotEmpty() &&
                        etDueDate.text.isNotEmpty() &&
                        etAmount.text.isNotEmpty() &&
                        etCategory.text.isNotEmpty()
                ){
                    val data = Bill(
                            null,
                            etName.text.toString(),
                            etAmount.text.toString().toInt(),
                            etDueDate.text.toString(),
                            null,
                            etCategory.text.toString(),
                            etNotes.text.toString()
                    )

                    saveData(data)
                } else {
                    showMessage(getString(R.string.title_is_not_empty))
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupDate(){
        with(binding){
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this@AddBillActivity, { _, y, m, d ->
                date = "$d-$m-$y"

                etDueDate.setText(dateFormat(
                        date,
                        "dd-mm-yyyy",
                        "dd MMMM yyyy"
                ))
            }, year, month, day)

            datePicker.show()
        }
    }

    private fun showMessage(message: String){
        Toasty.warning(this, message, Toast.LENGTH_SHORT, true).show()
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