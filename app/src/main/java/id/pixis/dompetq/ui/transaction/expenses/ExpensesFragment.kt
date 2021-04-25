package id.pixis.dompetq.ui.transaction.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.databinding.FragmentExpensesBinding
import id.pixis.dompetq.ui.bill.BillAdapter

@AndroidEntryPoint
class ExpensesFragment : Fragment() {

    private val binding : FragmentExpensesBinding by lazy {
        FragmentExpensesBinding.inflate(layoutInflater)
    }

    private val adapter: ExpensesAdapter by lazy {
        ExpensesAdapter {item -> showDetail(item)}
    }

    private val viewModel : ExpensesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewModel()
    }

    private fun setupAdapter(){
        with(binding){
            rvExpenses.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                        requireContext(), LinearLayoutManager.VERTICAL, false
                )
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupViewModel(){
        viewModel.data.observe(viewLifecycleOwner, adapter::submitList)
        viewModel.getExpenses(viewLifecycleOwner)
    }

    private fun showDetail(item: Transactions) {

    }

    override fun onResume() {
        super.onResume()
        viewModel.getExpenses(viewLifecycleOwner)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}