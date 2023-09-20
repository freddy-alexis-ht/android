package com.sunday.appteoria.di

import android.content.Context
import com.sunday.appteoria.data.source.datastore.DataStoreImpl
import com.sunday.appteoria.data.source.datastore.IDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): IDataStore {
        return DataStoreImpl(context)
    }
}