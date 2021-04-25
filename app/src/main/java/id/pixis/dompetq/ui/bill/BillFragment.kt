package id.pixis.dompetq.ui.bill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.databinding.FragmentBillBinding


@AndroidEntryPoint
class BillFragment : Fragment() {

    private val binding : FragmentBillBinding by lazy {
        FragmentBillBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewModel()
        setupListener()
    }

    private fun setupAdapter(){

    }

    private fun setupViewModel(){

    }

    private fun setupListener(){

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}