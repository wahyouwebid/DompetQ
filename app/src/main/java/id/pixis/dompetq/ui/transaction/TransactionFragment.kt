package id.pixis.dompetq.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.R
import id.pixis.dompetq.databinding.AdapterTabBinding
import id.pixis.dompetq.databinding.FragmentTransactionBinding
import id.pixis.dompetq.ui.adapter.TabAdapter
import id.pixis.dompetq.ui.transaction.expenses.ExpensesFragment
import id.pixis.dompetq.ui.transaction.income.IncomeFragment


@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private val binding : FragmentTransactionBinding by lazy {
        FragmentTransactionBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            when (position) {
                0 -> { tab.customView = getTabLayout(getString(R.string.title_income)) }
                1 -> { tab.customView = getTabLayout(getString(R.string.title_expenses)) }
            }
        }.attach()
        binding.pager.setCurrentItem(0, true)
    }

    private fun getTabLayout(title: String): View {
        val tabBinding = AdapterTabBinding.inflate(layoutInflater)
        tabBinding.tvTitle.text = title
        return tabBinding.root
    }

    private val adapter : TabAdapter by lazy {
        TabAdapter(
                this,
                arrayListOf(
                        IncomeFragment(),
                        ExpensesFragment()
                )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}