package id.pixis.dompetq.ui.bill

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(
        private val repository: Repository,
        private val disposable: CompositeDisposable
) : ViewModel() {

    val data : MutableLiveData<PagedList<Bill>> by lazy {
        MutableLiveData()
    }

    fun getData(owner: LifecycleOwner){
        repository.getAllBill(owner, data)
    }

    fun saveData(data: Bill){
        repository.addBill(data)
    }
}