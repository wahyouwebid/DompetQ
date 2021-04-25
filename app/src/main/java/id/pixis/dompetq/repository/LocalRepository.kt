package id.pixis.dompetq.repository

import id.pixis.dompetq.data.database.RoomDB
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val db : RoomDB,
    private val disposable: CompositeDisposable
) : Repository {

    override fun getDisposible(): CompositeDisposable {
        return disposable
    }

    override fun getDatabase(): RoomDB {
        return db
    }

}