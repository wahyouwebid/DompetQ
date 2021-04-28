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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val localRepository: LocalRepository
) : Repository {
    override fun addBill(data: Bill) {
        localRepository.addBill(data)
    }

    override fun getAllBill(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Bill>>
    ) {
        localRepository.getAllBill(owner, state)
    }

    override fun deleteBill(data: Bill, state: MutableLiveData<Boolean>) {
        localRepository.deleteBill(data, state)
    }

    override fun addCategories(data: Categories) {
        localRepository.addCategories(data)
    }

    override fun getAllTransaction(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getAllTransaction(owner, state)
    }

    override fun deleteTransaction(data: Transactions, state: MutableLiveData<Boolean>) {
        localRepository.deleteTransaction(data, state)
    }

    override fun getAllIncome(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getAllIncome(owner, state)
    }

    override fun getAllExpenses(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getAllExpenses(owner, state)
    }

    override suspend fun getTotalIncome(): SumAmount? {
        return localRepository.getTotalIncome()
    }

    override fun getTotalIncomeMonth(
            startDate: String,
            endDate: String,
            state: MutableLiveData<SumAmount>
    ) {
        localRepository.getTotalIncomeMonth(startDate, endDate, state)
    }

    override fun getTotalIncomeWeek(
        startDate: String,
        endDate: String,
        state: MutableLiveData<SumAmount>
    ) {
        localRepository.getTotalIncomeWeek(startDate, endDate, state)
    }

    override fun getTotalIncomeDay(date: String, state: MutableLiveData<SumAmount>) {
        localRepository.getTotalIncomeDay(date, state)
    }

    override suspend fun getTotalExpenses(): SumAmount? {
        return localRepository.getTotalExpenses()
    }

    override fun getTotalExpensesMonth(
            startDate: String,
            endDate: String,
            state: MutableLiveData<SumAmount>
    ) {
        localRepository.getTotalExpensesMonth(startDate, endDate, state)
    }

    override fun getTotalExpensesWeek(
        startDate: String,
        endDate: String,
        state: MutableLiveData<SumAmount>
    ) {
        localRepository.getTotalExpensesWeek(startDate, endDate, state)
    }

    override fun getTotalExpensesDay(date: String, state: MutableLiveData<SumAmount>) {
        localRepository.getTotalExpensesDay(date, state)
    }

    override fun getTransactionByDay(
            date: String,
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getTransactionByDay(date, owner, state)
    }

    override fun getTransactionByMonth(
            startDate: String,
            endDate: String,
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getTransactionByMonth(startDate, endDate, owner, state)
    }

    override fun getTransactionByWeek(
            startDate: String,
            endDate: String,
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getTransactionByWeek(startDate, endDate, owner, state)
    }

    override fun addTransaction(data: Transactions) {
        localRepository.addTransaction(data)
    }

    override fun getAllCategories(owner: LifecycleOwner, state: MutableLiveData<PagedList<Categories>>) {
        localRepository.getAllCategories(owner, state)
    }

    override fun getCategoriesByType(type: Int, owner: LifecycleOwner, state: MutableLiveData<PagedList<Categories>>) {
        localRepository.getCategoriesByType(type, owner, state)
    }

    override fun getTotalBill(state: MutableLiveData<SumAmount>) {
        localRepository.getTotalBill(state)
    }

    override fun getDisposible(): CompositeDisposable {
        return localRepository.getDisposible()
    }

    override fun getDatabase(): RoomDB {
        return localRepository.getDatabase()
    }
}