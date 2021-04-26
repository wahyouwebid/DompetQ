package id.pixis.dompetq.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.R
import id.pixis.dompetq.databinding.FragmentHomeBinding
import id.pixis.dompetq.ui.transaction.income.IncomeViewModel
import id.pixis.dompetq.utils.Converter
import id.pixis.dompetq.utils.Utils


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding : FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupViewModel()
        setupRadioButton()
    }

    private fun setupData(){
        viewModel.getTotalIncomeMonth(Utils.getFirstDate(), Utils.getLastDate())
        viewModel.getTotalExpensesMonth(Utils.getFirstDate(), Utils.getLastDate())
    }

    private fun setupViewModel(){
        with(binding){
            viewModel.totalIncome.observe(viewLifecycleOwner, {
                tvIncome.text = Converter.currencyIdr(it.total.toInt())
            })

            viewModel.totalExpenses.observe(viewLifecycleOwner, {
                tvExpenses.text = Converter.currencyIdr(it.total.toInt())
            })
        }
    }

    private fun setupRadioButton(){
        with(binding){
            rbGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rbMonth -> {
                        viewModel.getTotalIncomeMonth(Utils.getFirstDate(), Utils.getLastDate())
                        viewModel.getTotalExpensesMonth(Utils.getFirstDate(), Utils.getLastDate())
                    }
                    R.id.rbWeek -> {
                        viewModel.getTotalIncomeWeek(Utils.getLastWeek(7), Utils.getCurrentDate())
                        viewModel.getTotalExpensesWeek(Utils.getLastWeek(7), Utils.getCurrentDate())
                    }
                    R.id.rbDay -> {
                        viewModel.getTotalIncomeDay(Utils.getCurrentDate())
                        viewModel.getTotalExpensesDay(Utils.getCurrentDate())
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(binding){
            when {
                rbMonth.isChecked -> {
                    viewModel.getTotalIncomeMonth(Utils.getFirstDate(), Utils.getLastDate())
                    viewModel.getTotalExpensesMonth(Utils.getFirstDate(), Utils.getLastDate())
                }
                rbWeek.isChecked -> {
                    viewModel.getTotalIncomeWeek(Utils.getLastWeek(7), Utils.getCurrentDate())
                    viewModel.getTotalExpensesWeek(Utils.getLastWeek(7), Utils.getCurrentDate())
                }
                else -> {
                    viewModel.getTotalIncomeDay(Utils.getCurrentDate())
                    viewModel.getTotalExpensesDay(Utils.getCurrentDate())
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}