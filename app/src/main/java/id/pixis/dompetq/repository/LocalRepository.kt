package id.pixis.dompetq.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.pixis.dompetq.data.database.RoomDB
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.data.model.SumAmount
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val db : RoomDB,
    private val disposable: CompositeDisposable
) : Repository {
    override fun addBill(data: Bill) {
        db.billDao().add(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable()
                .subscribe()
                .let { return@let disposable::add }
    }

    override fun getAllBill(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Bill>>
    ) {
        LivePagedListBuilder(
                db.billDao().getAll(),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun addTransaction(data: Transactions) {
        db.transactionDao().add(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable()
                .subscribe()
                .let { return@let disposable::add }
    }

    override fun getAllTransaction(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getAll(),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getAllIncome(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getAllIncome(),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getAllExpenses(
            owner: LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getAllExpenses(),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getTotalIncomeMonth(
            startDate: String,
            endDate: String,
            state : MutableLiveData<SumAmount>
    ){
        db.transactionDao().getTotalIncomeMonth(startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable()
                .subscribe(state::postValue)
                .let { return@let disposable::add }
    }

    override fun getTotalExpensesMonth(
            startDate: String,
            endDate: String,
            state : MutableLiveData<SumAmount>
    ) {
        db.transactionDao().getTotalExpensesMonth(startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable()
                .subscribe(state::postValue)
                .let { return@let disposable::add }
    }

    override fun getByDay(
            date: String,
            owner : LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getByDay(date),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getByMonth(
            startDate: String,
            endDate: String, owner : LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getByMonth(startDate, endDate),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getByWeek(
            startDate: String,
            endDate: String,
            owner : LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getByWeek(startDate, endDate),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getDisposible(): CompositeDisposable {
        return disposable
    }

    override fun getDatabase(): RoomDB {
        return db
    }

}