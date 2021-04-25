package id.pixis.dompetq.repository

import id.pixis.dompetq.data.database.RoomDB
import io.reactivex.disposables.CompositeDisposable

interface Repository {
    fun getDisposible() : CompositeDisposable
    fun getDatabase() : RoomDB
}