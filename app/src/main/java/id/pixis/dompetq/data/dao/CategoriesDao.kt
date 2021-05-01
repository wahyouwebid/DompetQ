package id.pixis.dompetq.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.pixis.dompetq.data.entity.Categories
import io.reactivex.Single

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data : Categories) : Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(data : Categories) : Single<Long>

    @Query("SELECT * FROM categories ORDER BY id DESC")
    fun getAllCategories() : DataSource.Factory<Int, Categories>

    @Query("SELECT * FROM categories WHERE type =:type ORDER BY id ASC")
    fun getCategoriesByType(type : Int) : DataSource.Factory<Int, Categories>

}