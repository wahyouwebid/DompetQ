package id.pixis.dompetq.repository

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import id.pixis.dompetq.data.database.RoomDB
import id.pixis.dompetq.data.entity.Bill
import io.reactivex.disposables.CompositeDisposable

interface Repository {

    fun addBill(data : Bill)
    fun getAllBill(owner : LifecycleOwner, state : MutableLiveData<PagedList<Bill>>)

    fun getDisposible() : CompositeDisposable
    fun getDatabase() : RoomDB
}