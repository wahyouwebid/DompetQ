package id.pixis.dompetq.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.pixis.dompetq.data.dao.BillDao
import id.pixis.dompetq.data.dao.CategoriesDao
import id.pixis.dompetq.data.dao.TransactionDao
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.data.entity.Categories
import id.pixis.dompetq.data.entity.Transactions

@Database(
    entities = [
        Bill::class,
        Transactions::class,
        Categories::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {

    abstract fun billDao() : BillDao
    abstract fun transactionDao() : TransactionDao
    abstract fun categoriesDao() : CategoriesDao

    companion object {

        @Volatile private var instance : RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "TabunganQ.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}