package id.pixis.dompetq.ui.bill.add

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.pixis.dompetq.data.entity.Bill
import id.pixis.dompetq.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddBillViewModel @Inject constructor(
        private val repository: Repository,
        private val disposable: CompositeDisposable
) : ViewModel() {

    fun saveData(data: Bill){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addBill(data)
        }
    }
}