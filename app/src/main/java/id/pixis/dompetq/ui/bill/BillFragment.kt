package id.pixis.dompetq.ui.bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.databinding.FragmentBillBinding
import id.pixis.dompetq.utils.Converter


@AndroidEntryPoint
class BillFragment : Fragment() {

    private val adapter: BillAdapter by lazy {
        BillAdapter {item -> showDetail(item)}
    }

    private val binding : FragmentBillBinding by lazy {
        FragmentBillBinding.inflate(layoutInflater)
    }

    private val viewModel : BillViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewModel()
    }

    private fun setupAdapter(){
        with(binding){
            rvBill.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                        requireContext(), LinearLayoutManager.VERTICAL, false
                )
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupViewModel(){
        with(binding){
            viewModel.getData(viewLifecycleOwner)
            viewModel.getTotalBill()
            viewModel.data.observe(viewLifecycleOwner, adapter::submitList)
            viewModel.totalBill.observe(viewLifecycleOwner, {
                val totalAmount = Converter.currencyIdr(it.total.toInt())
                tvTotal.text = totalAmount?.replace(",00", "")
            })
        }
    }

    private fun showDetail(item: Bill) {

    }

    override fun onResume() {
        super.onResume()
        viewModel.getData(viewLifecycleOwner)
        viewModel.getTotalBill()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}