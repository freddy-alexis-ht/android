package com.sunday.appteoria.data.repository

import com.sunday.appteoria.data.source.datastore.IDataStore
import com.sunday.appteoria.domain.repository.IRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataStore: IDataStore
): IRepository {

    override suspend fun setDarkTheme(key: String, value: Boolean) {
        dataStore.saveDarkTheme(key, value)
    }

    override suspend fun getDarkTheme(key: String): Boolean? {
        return dataStore.readDarkTheme(key)
    }
}