package id.pixis.dompetq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.pixis.dompetq.data.database.RoomDB
import id.pixis.dompetq.repository.DataRepository
import id.pixis.dompetq.repository.LocalRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideLocalRepository(
        db : RoomDB,
        disposable: CompositeDisposable
    ) : LocalRepository = LocalRepository(db, disposable)

    @Provides
    @Singleton
    fun provideDataRepository(
        localRepository: LocalRepository
    ) : DataRepository = DataRepository(localRepository)

    @Singleton
    @Provides
    fun provideDisposible() : CompositeDisposable = CompositeDisposable()
}