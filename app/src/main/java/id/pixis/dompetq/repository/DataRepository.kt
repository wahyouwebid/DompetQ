package id.pixis.dompetq.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import id.pixis.dompetq.data.database.RoomDB
import id.pixis.dompetq.data.entity.Bill
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

    override fun getAllBill(owner: LifecycleOwner, state: MutableLiveData<PagedList<Bill>>) {
        localRepository.getAllBill(owner, state)
    }

    override fun getDisposible(): CompositeDisposable {
        return localRepository.getDisposible()
    }

    override fun getDatabase(): RoomDB {
        return localRepository.getDatabase()
    }
}