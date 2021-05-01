package id.pixis.dompetq.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.R
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.databinding.FragmentHomeBinding
import id.pixis.dompetq.utils.Converter
import id.pixis.dompetq.utils.Utils


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding : FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel : HomeViewModel by viewModels()

    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter {item -> showDetail(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupData()
        setupViewModel()
        setupRadioButton()
    }

    private fun setupAdapter(){
        with(binding){
            rvTransaction.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
            }
        }
    }

    private fun setupData(){
        viewModel.getTotalIncomeMonth(Utils.getFirstDate(), Utils.getLastDate())
        viewModel.getTotalExpensesMonth(Utils.getFirstDate(), Utils.getLastDate())
        viewModel.getBalance()
        viewModel.getTransaction(viewLifecycleOwner)
    }

    private fun setupViewModel(){
        with(binding){
            viewModel.totalIncome.observe(viewLifecycleOwner, {
                val totalAmount = Converter.currencyIdr(it.total.toInt())
                tvIncome.text = totalAmount?.replace(",00", "")

            })

            viewModel.totalExpenses.observe(viewLifecycleOwner, {
                val totalAmount = Converter.currencyIdr(it.total.toInt())
                tvExpenses.text = totalAmount?.replace(",00", "")
            })

            viewModel.balance.observe(viewLifecycleOwner, {
                val income = it.income?.total
                val expenses = it.expenses?.total
                val balance = (expenses?.let { i -> income?.minus(i) })
                tvCurrentBalance.text = balance?.toInt()?.let {
                        balances -> Converter.currencyIdr(balances)?.replace(",00","")
                }
            })

            viewModel.data.observe(viewLifecycleOwner, adapter::submitList)

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
        viewModel.getBalance()
        viewModel.getTransaction(viewLifecycleOwner)
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

    private fun showDetail(item: Transactions) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}