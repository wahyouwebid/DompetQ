package id.pixis.dompetq.ui.transaction.expenses

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.data.model.SumAmount
import id.pixis.dompetq.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    val data : MutableLiveData<PagedList<Transactions>> by lazy {
        MutableLiveData()
    }

    val totalExpenses: MutableLiveData<SumAmount> by lazy {
        MutableLiveData()
    }

    fun getExpenses(owner: LifecycleOwner){
        repository.getAllExpenses(owner, data)
    }

    fun getTotalExpenses(startDate: String, endDate: String){
        repository.getTotalExpensesMonth(startDate, endDate, totalExpenses)
    }
}