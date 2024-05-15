package almerti.egline.data.di

import almerti.egline.data.repository.FolderRepository
import almerti.egline.data.repository.FolderRepositoryimpl
import almerti.egline.data.repository.UserRepository
import almerti.egline.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindUserRepository(UserRepository : UserRepositoryImpl) : UserRepository

    @Binds
    internal abstract fun bindFolderRepository(FolderRepository : FolderRepositoryimpl) : FolderRepository

}
