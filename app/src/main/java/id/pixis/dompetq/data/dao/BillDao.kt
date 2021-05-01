package id.pixis.dompetq.data.dao

import androidx.paging.DataSource
import androidx.room.*
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.data.model.SumAmount
import io.reactivex.Single

@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data : Bill) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(data : Bill) : Single<Long>

    @Delete
    fun delete(data : Bill) :Single<Int>

    @Query("SELECT * FROM bill ORDER BY id DESC")
    fun getAll() : DataSource.Factory<Int, Bill>

    @Query("SELECT SUM(amount) as total FROM bill where billStatus = 0")
    fun getTotalBill(): Single<SumAmount?>
}