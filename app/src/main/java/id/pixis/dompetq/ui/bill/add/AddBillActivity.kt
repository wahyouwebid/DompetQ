package id.pixis.dompetq.ui.bill.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import id.pixis.dompetq.R
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.data.entity.Categories
import id.pixis.dompetq.databinding.ActivityAddBillBinding
import id.pixis.dompetq.databinding.BottomSheetCategoriesBinding
import id.pixis.dompetq.ui.bill.BillViewModel
import id.pixis.dompetq.ui.transaction.add.CategoriesAdapter
import id.pixis.dompetq.utils.Converter
import id.pixis.dompetq.utils.Converter.dateFormat
import java.util.*

@AndroidEntryPoint
class AddBillActivity : AppCompatActivity() {

    private val binding : ActivityAddBillBinding by lazy {
        ActivityAddBillBinding.inflate(layoutInflater)
    }

    private val viewModel : AddBillViewModel by viewModels()

    private val dialog : BottomSheetDialog by lazy {
        BottomSheetDialog(this)
    }

    private val adapter: CategoriesAdapter by lazy {
        CategoriesAdapter(
                showDetail = { item -> showDetail(item) }
        )
    }

    private var typeTransaction : Int = 1
    private var iconCategories = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
        setupStatusBar()
        setupListener()
    }

    private fun setupViewModel(){
        viewModel.getCategoriesByType(typeTransaction, this)
        viewModel.categories.observe(this, adapter::submitList)
    }


    private fun setupListener(){
        with(binding){
            imgBack.setOnClickListener { finish() }
            etDueDate.setOnClickListener { setupDate() }
            etCategory.setOnClickListener { setupBottomSheet() }
            btnSave.setOnClickListener {

                if (
                        etName.text.isNotEmpty() &&
                        etDueDate.text.isNotEmpty() &&
                        etAmount.text.isNotEmpty() &&
                        etCategory.text.isNotEmpty()
                ){

                    val date = dateFormat(
                            etDueDate.text.toString(),
                            "dd MMMM yyyy",
                            "yyyyMMdd"
                    )

                    val data = Bill(
                            null,
                            etName.text.toString(),
                            etAmount.text.toString().toInt(),
                            date,
                            etNotes.text.toString(),
                            false,
                            etCategory.text.toString(),
                            iconCategories,
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
            DatePickerDialog(
                    this@AddBillActivity, { _, year, month, dayOfMonth ->
                etDueDate.setText(
                        dateFormat(
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

    private fun setupBottomSheet(){
        val categoriesBinding = BottomSheetCategoriesBinding.inflate(layoutInflater)
        dialog.setContentView(categoriesBinding.root)
        with(categoriesBinding) {
            rvCategories.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                        this@AddBillActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                )
            }
        }

        dialog.show()
    }

    private fun showDetail(item: Categories) {
        with(binding){
            etCategory.setText(item.name)
            iconCategories = item.icon
            dialog.dismiss()
        }
    }
}