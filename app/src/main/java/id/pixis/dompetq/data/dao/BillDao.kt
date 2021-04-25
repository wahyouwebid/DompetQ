package id.pixis.dompetq.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.pixis.dompetq.data.entity.Bill
import io.reactivex.Single

@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data : Bill) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(data : Bill) : Single<Long>

    @Query("SELECT * FROM bill ORDER BY id DESC")
    fun getAll() : DataSource.Factory<Int, Bill>

}