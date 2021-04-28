package id.pixis.dompetq.data.dao

import androidx.paging.DataSource
import androidx.room.*
import id.pixis.dompetq.data.entity.Transactions
import id.pixis.dompetq.data.model.SumAmount
import io.reactivex.Single

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data : Transactions) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(data : Transactions) : Single<Long>

    @Delete
    fun delete(data : Transactions) :Single<Int>

    @Query("SELECT * FROM transactions ORDER BY id DESC LIMIT 10")
    fun getAllTransaction() : DataSource.Factory<Int, Transactions>

    @Query("SELECT * FROM transactions WHERE type = 0 ORDER BY id DESC")
    fun getAllIncome() : DataSource.Factory<Int, Transactions>

    @Query("SELECT * FROM transactions WHERE type = 1 ORDER BY id DESC")
    fun getAllExpenses() : DataSource.Factory<Int, Transactions>

    @Query("SELECT SUM(amount) as total FROM transactions where type = 0")
    suspend fun getTotalIncome(): SumAmount?

    @Query("SELECT SUM(amount) as total FROM transactions where type = 0 AND date BETWEEN :startDate AND :endDate")
    fun getTotalIncomeMonth(
            startDate: String,
            endDate: String
    ): Single<SumAmount?>

    @Query("SELECT SUM(amount) as total FROM transactions where type = 0 AND date BETWEEN :startDate AND :endDate")
    fun getTotalIncomeWeek(
        startDate: String,
        endDate: String
    ): Single<SumAmount?>

    @Query("SELECT SUM(amount) as total FROM transactions where type = 0 AND date=:date")
    fun getTotalIncomeDay(
        date: String
    ): Single<SumAmount?>

    @Query("SELECT SUM(amount) as total FROM transactions where type = 1")
    suspend fun getTotalExpenses(): SumAmount?

    @Query("SELECT SUM(amount) as total FROM transactions where type = 1 AND date BETWEEN :startDate AND :endDate")
    fun getTotalExpensesMonth(
            startDate: String,
            endDate: String
    ): Single<SumAmount?>

    @Query("SELECT SUM(amount) as total FROM transactions where type = 1 AND date BETWEEN :startDate AND :endDate")
    fun getTotalExpensesWeek(
        startDate: String,
        endDate: String
    ): Single<SumAmount?>

    @Query("SELECT SUM(amount) as total FROM transactions where type = 1 AND date=:date")
    fun getTotalExpensesDay(
        date: String
    ): Single<SumAmount?>

    @Query("SELECT * FROM transactions where date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getTransactionByMonth(
            startDate: String,
            endDate: String
    ) : DataSource.Factory<Int, Transactions>

    @Query("SELECT * FROM transactions where date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getTransactionByWeek(
            startDate: String,
            endDate: String
    ) : DataSource.Factory<Int, Transactions>

    @Query("SELECT * FROM transactions where date = :date ORDER BY date DESC")
    fun getTransactionByDay(date : String) : DataSource.Factory<Int, Transactions>

}