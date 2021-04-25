package id.pixis.dompetq.ui.transaction.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.WindowManager
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import id.pixis.dompetq.R
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.databinding.ActivityAddTransactionBinding
import id.pixis.dompetq.ui.transaction.TransactionViewModel
import id.pixis.dompetq.utils.Converter
import java.util.*


@AndroidEntryPoint
class AddTransactionActivity : AppCompatActivity() {

    private val binding : ActivityAddTransactionBinding by lazy {
        ActivityAddTransactionBinding.inflate(layoutInflater)
    }

    private val viewModel : TransactionViewModel by viewModels()

    private lateinit var date : String
    private var typeTransaction : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        setupListener()
        setupRadioButton()
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
                    val data = Transactions(
                        null,
                        etName.text.toString(),
                        etAmount.text.toString().toInt(),
                        etDueDate.text.toString(),
                        etNotes.text.toString(),
                        typeTransaction,
                        etCategory.text.toString(),
                        null
                    )

                    saveData(data)
                } else {
                    showMessage(getString(R.string.title_is_not_empty))
                }
            }
        }
    }

    private fun setupRadioButton(){
        with(binding){
            rbGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.rbPemasukan) {
                    typeTransaction = 0
                } else if (checkedId == R.id.rbPengeluaran) {
                    typeTransaction = 1
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupDate(){
        with(binding){
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this@AddTransactionActivity, { _, y, m, d ->
                date = "$d-$m-$y"

                etDueDate.setText(
                    Converter.dateFormat(
                        date,
                        "dd-mm-yyyy",
                        "dd MMMM yyyy"
                    )
                )
            }, year, month, day)

            datePicker.show()
        }
    }

    private fun showMessage(message: String){
        Toasty.warning(this, message, Toast.LENGTH_SHORT, true).show()
    }

    private fun saveData(data: Transactions){
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