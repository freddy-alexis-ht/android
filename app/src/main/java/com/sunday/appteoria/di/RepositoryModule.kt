package com.sunday.appteoria.di

import com.sunday.appteoria.data.repository.RepositoryImpl
import com.sunday.appteoria.domain.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(
        repositoryImpl: RepositoryImpl
    ): IRepository
}