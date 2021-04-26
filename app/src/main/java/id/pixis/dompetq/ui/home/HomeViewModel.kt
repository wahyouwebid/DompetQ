package id.pixis.dompetq.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.data.model.ChartObject
import id.pixis.dompetq.data.model.SumAmount
import id.pixis.dompetq.repository.Repository
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    val data : MutableLiveData<PagedList<Transactions>> by lazy {
        MutableLiveData()
    }

    val balance: MutableLiveData<ChartObject> by lazy {
        MutableLiveData<ChartObject>()
    }

    val totalIncome : MutableLiveData<SumAmount> by lazy {
        MutableLiveData()
    }

    val totalExpenses : MutableLiveData<SumAmount> by lazy {
        MutableLiveData()
    }

    fun getTotalIncomeMonth(startDate: String, endDate: String){
        repository.getTotalIncomeMonth(startDate, endDate, totalIncome)
    }

    fun getTotalIncomeWeek(startDate: String, endDate: String){
        repository.getTotalIncomeMonth(startDate, endDate, totalIncome)
    }

    fun getTotalIncomeDay(date : String){
        repository.getTotalIncomeDay(date, totalIncome)
    }

    fun getTotalExpensesMonth(startDate: String, endDate: String){
        repository.getTotalExpensesMonth(startDate, endDate, totalExpenses)
    }

    fun getTotalExpensesWeek(startDate: String, endDate: String){
        repository.getTotalExpensesWeek(startDate, endDate, totalExpenses)
    }

    fun getTotalExpensesDay(date: String){
        repository.getTotalExpensesDay(date, totalExpenses)
    }

    fun getBalance() {
        CoroutineScope(Dispatchers.IO).launch {
            val income = async {
                repository.getTotalIncome()
            }
            val expenses = async {
                repository.getTotalExpenses()
            }

            withContext(Dispatchers.Main) {
                balance.postValue(
                    ChartObject(
                        income.await(),
                        expenses.await()
                    )
                )
            }
        }
    }

    fun getTransaction(owner: LifecycleOwner){
        repository.getAllTransaction(owner, data)
    }
}