package id.pixis.dompetq.ui.transaction.income

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.databinding.FragmentIncomeBinding
import id.pixis.dompetq.utils.Converter
import id.pixis.dompetq.utils.Utils


@AndroidEntryPoint
class IncomeFragment : Fragment() {

    private val binding : FragmentIncomeBinding by lazy {
        FragmentIncomeBinding.inflate(layoutInflater)
    }

    private val adapter: IncomeAdapter by lazy {
        IncomeAdapter (
                showDetail = {item -> showDetail(item)},
                deleteItem = {item -> deleteData(item)}
        )
    }

    private val viewModel : IncomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupData()
        setupViewModel()
    }

    private fun setupAdapter(){
        with(binding){
            rvIncome.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                        requireContext(), LinearLayoutManager.VERTICAL, false
                )
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupData(){
        viewModel.getIncome(viewLifecycleOwner)
        viewModel.getTotalIncome(Utils.getFirstDate(), Utils.getLastDate())
    }

    private fun setupViewModel(){
        with(binding){
            viewModel.data.observe(viewLifecycleOwner, adapter::submitList)
            viewModel.totalIncome.observe(viewLifecycleOwner, {
                tvTotal.text = Converter.currencyIdr(it.total.toInt())
            })

            viewModel.totalIncome.observe(viewLifecycleOwner, {
                val totalAmount = Converter.currencyIdr(it.total.toInt())
                tvTotal.text = totalAmount?.replace(",00", "")
            })

            viewModel.delete.observe(viewLifecycleOwner, {
                viewModel.getIncome(viewLifecycleOwner)
            })
        }
    }

    private fun showDetail(item: Transactions) {

    }

    private fun deleteData(item: Transactions) {
        viewModel.deleteData(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTotalIncome(Utils.getFirstDate(), Utils.getLastDate())
        viewModel.getIncome(viewLifecycleOwner)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}