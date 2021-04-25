package id.pixis.dompetq.ui.transaction

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.repository.Repository
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    val data : MutableLiveData<PagedList<Transactions>> by lazy {
        MutableLiveData()
    }

    fun saveData(data: Transactions){
        repository.addTransaction(data)
    }
}