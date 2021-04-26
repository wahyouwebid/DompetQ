package id.pixis.dompetq.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import id.pixis.dompetq.data.database.RoomDB
import id.pixis.dompetq.data.entity.Bill
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

    override fun addTransaction(data: Transactions) {
        localRepository.addTransaction(data)
    }

    override fun getAllTransaction(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getAllTransaction(owner, state)
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

    override fun getTotalIncomeMonth(
            startDate: String,
            endDate: String,
            state: MutableLiveData<SumAmount>
    ) {
        localRepository.getTotalIncomeMonth(startDate, endDate, state)
    }

    override fun getTotalExpensesMonth(
            startDate: String,
            endDate: String,
            state: MutableLiveData<SumAmount>
    ) {
        localRepository.getTotalExpensesMonth(startDate, endDate, state)
    }

    override fun getByDay(
            date: String,
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getByDay(date, owner, state)
    }

    override fun getByMonth(
            startDate: String,
            endDate: String,
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getByMonth(startDate, endDate, owner, state)
    }

    override fun getByWeek(
            startDate: String,
            endDate: String,
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        localRepository.getByWeek(startDate, endDate, owner, state)
    }

    override fun getDisposible(): CompositeDisposable {
        return localRepository.getDisposible()
    }

    override fun getDatabase(): RoomDB {
        return localRepository.getDatabase()
    }
}