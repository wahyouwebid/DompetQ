package id.pixis.dompetq.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.pixis.dompetq.data.entity.Transactions
import io.reactivex.Single

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data : Transactions) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(data : Transactions) : Single<Long>

    @Query("SELECT * FROM transactions ORDER BY id DESC")
    fun getAll() : DataSource.Factory<Int, Transactions>

    @Query("SELECT * FROM transactions WHERE type = 0 ORDER BY id DESC")
    fun getAllIncome() : DataSource.Factory<Int, Transactions>

    @Query("SELECT * FROM transactions WHERE type = 1 ORDER BY id DESC")
    fun getAllExpenses() : DataSource.Factory<Int, Transactions>

}