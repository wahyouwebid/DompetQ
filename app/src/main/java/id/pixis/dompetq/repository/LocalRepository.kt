package id.pixis.dompetq.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.pixis.dompetq.data.database.RoomDB
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.data.entity.Categories
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

    override fun deleteBill(data: Bill, state: MutableLiveData<Boolean>) {
        db.billDao().delete(data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .toFlowable()
                .subscribe()
                .let { return@let disposable::add }
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
                db.transactionDao().getAllTransaction(),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun deleteTransaction(data: Transactions, state: MutableLiveData<Boolean>) {
        db.transactionDao().delete(data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .toFlowable()
                .subscribe()
                .let { return@let disposable::add }
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

    override suspend fun getTotalIncome(): SumAmount? {
        return db.transactionDao().getTotalIncome()
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

    override fun getTotalIncomeWeek(
        startDate: String,
        endDate: String,
        state: MutableLiveData<SumAmount>
    ) {
        db.transactionDao().getTotalIncomeWeek(startDate, endDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe(state::postValue)
            .let { return@let disposable::add }
    }

    override fun getTotalIncomeDay(date: String, state: MutableLiveData<SumAmount>) {
        db.transactionDao().getTotalIncomeDay(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe(state::postValue)
            .let { return@let disposable::add }
    }

    override suspend fun getTotalExpenses(): SumAmount? {
        return db.transactionDao().getTotalExpenses()
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

    override fun getTotalExpensesWeek(
        startDate: String,
        endDate: String,
        state: MutableLiveData<SumAmount>
    ) {
        db.transactionDao().getTotalExpensesWeek(startDate, endDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe(state::postValue)
            .let { return@let disposable::add }
    }

    override fun getTotalExpensesDay(date: String, state: MutableLiveData<SumAmount>) {
        db.transactionDao().getTotalExpensesDay(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe(state::postValue)
            .let { return@let disposable::add }
    }

    override fun getTransactionByDay(
            date: String,
            owner : LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getTransactionByDay(date),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getTransactionByMonth(
            startDate: String,
            endDate: String, owner : LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getTransactionByMonth(startDate, endDate),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getTransactionByWeek(
            startDate: String,
            endDate: String,
            owner : LifecycleOwner,
            state: MutableLiveData<PagedList<Transactions>>
    ) {
        LivePagedListBuilder(
                db.transactionDao().getTransactionByWeek(startDate, endDate),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun addCategories(data: Categories) {
        db.categoriesDao().add(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable()
                .subscribe()
                .let { return@let disposable::add }
    }

    override fun getAllCategories(owner: LifecycleOwner, state: MutableLiveData<PagedList<Categories>>) {
        LivePagedListBuilder(
                db.categoriesDao().getAllCategories(),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getCategoriesByType(type: Int, owner: LifecycleOwner, state: MutableLiveData<PagedList<Categories>>) {
        LivePagedListBuilder(
                db.categoriesDao().getCategoriesByType(type),
                10
        ).build().observe(owner, state::postValue)
    }

    override fun getTotalBill( state: MutableLiveData<SumAmount>) {
        db.billDao().getTotalBill()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable()
                .subscribe(state::postValue)
                .let { return@let disposable::add }
    }

    override fun getDisposible(): CompositeDisposable {
        return disposable
    }

    override fun getDatabase(): RoomDB {
        return db
    }

}