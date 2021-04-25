package id.pixis.dompetq.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import id.pixis.dompetq.databinding.FragmentTransactionBinding


@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private val binding : FragmentTransactionBinding by lazy {
        FragmentTransactionBinding.inflate(layoutInflater)
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