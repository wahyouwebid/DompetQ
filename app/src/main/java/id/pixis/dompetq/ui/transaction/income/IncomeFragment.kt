package id.pixis.dompetq.ui.transaction.income

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.databinding.FragmentIncomeBinding


@AndroidEntryPoint
class IncomeFragment : Fragment() {

    private val binding : FragmentIncomeBinding by lazy {
        FragmentIncomeBinding.inflate(layoutInflater)
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