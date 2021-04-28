package id.pixis.dompetq.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import id.pixis.dompetq.data.database.RoomDB
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.data.entity.Categories
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.data.model.SumAmount
import io.reactivex.disposables.CompositeDisposable

interface Repository {

    fun addBill(data : Bill)

    fun getAllBill(owner : LifecycleOwner, state : MutableLiveData<PagedList<Bill>>)

    fun deleteBill(data : Bill, state : MutableLiveData<Boolean>)

    fun addTransaction(data : Transactions)

    fun getAllTransaction(
            owner : LifecycleOwner,
            state : MutableLiveData<PagedList<Transactions>>
    )

    fun deleteTransaction(data : Transactions, state : MutableLiveData<Boolean>)

    fun getAllIncome(
            owner : LifecycleOwner,
            state : MutableLiveData<PagedList<Transactions>>
    )
    fun getAllExpenses(
            owner : LifecycleOwner,
            state : MutableLiveData<PagedList<Transactions>>
    )

    suspend fun getTotalIncome() : SumAmount?

    fun getTotalIncomeMonth(
            startDate : String,
            endDate : String,
            state : MutableLiveData<SumAmount>
    )
    fun getTotalIncomeWeek(
        startDate : String,
        endDate : String,
        state : MutableLiveData<SumAmount>
    )
    fun getTotalIncomeDay(
        date : String,
        state : MutableLiveData<SumAmount>
    )

    suspend fun getTotalExpenses() : SumAmount?

    fun getTotalExpensesMonth(
            startDate : String,
            endDate : String,
            state : MutableLiveData<SumAmount>
    )
    fun getTotalExpensesWeek(
        startDate : String,
        endDate : String,
        state : MutableLiveData<SumAmount>
    )
    fun getTotalExpensesDay(
        date : String,
        state : MutableLiveData<SumAmount>
    )
    fun getTransactionByDay(
            date : String,
            owner : LifecycleOwner,
            state : MutableLiveData<PagedList<Transactions>>
    )
    fun getTransactionByMonth(
            startDate : String,
            endDate : String,
            owner : LifecycleOwner,
            state : MutableLiveData<PagedList<Transactions>>
    )
    fun getTransactionByWeek(
            startDate : String,
            endDate : String,
            owner : LifecycleOwner,
            state : MutableLiveData<PagedList<Transactions>>
    )

    fun addCategories(data : Categories)

    fun getAllCategories(
            owner : LifecycleOwner,
            state : MutableLiveData<PagedList<Categories>>
    )

    fun getCategoriesByType(
            type : Int,
            owner : LifecycleOwner,
            state : MutableLiveData<PagedList<Categories>>
    )

    fun getTotalBill(
            state : MutableLiveData<SumAmount>
    )

    fun getDisposible() : CompositeDisposable
    fun getDatabase() : RoomDB
}