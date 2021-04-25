package id.pixis.dompetq.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.pixis.dompetq.repository.DataRepository
import id.pixis.dompetq.repository.Repository

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindRepository(
        dataRepository: DataRepository
    ) : Repository
}