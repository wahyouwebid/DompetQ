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

                    val date = Converter.dateFormat(
                            etDueDate.text.toString(),
                            "dd MMMM yyyy",
                            "yyyyMMdd"
                    )

                    val data = Transactions(
                        null,
                        etName.text.toString(),
                        etAmount.text.toString().toInt(),
                            date,
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
            etDueDate.also {
                it.setOnClickListener {
                    DatePickerDialog(
                            this@AddTransactionActivity, { _, year, month, dayOfMonth ->
                            etDueDate.setText(
                                    Converter.dateFormat(
                                            "$dayOfMonth-" + Converter.decimalFormat(
                                                    month + 1
                                            ) + "-$year",
                                            "dd-MM-yyyy",
                                            "dd MMMM yyyy"
                                    )
                            )},
                            Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            }
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