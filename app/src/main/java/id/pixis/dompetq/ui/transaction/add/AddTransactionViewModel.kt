package id.pixis.dompetq.ui.transaction.add

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pixis.dompetq.data.entity.Categories
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.repository.Repository
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    val categories : MutableLiveData<PagedList<Categories>> by lazy {
        MutableLiveData()
    }

    fun saveData(data: Transactions){
        repository.addTransaction(data)
    }

    fun getCategoriesByType(type: Int, owner: LifecycleOwner) {
        repository.getCategoriesByType(type, owner, categories)
    }
}