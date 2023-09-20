package com.sunday.appteoria.data.repository

import com.sunday.appteoria.data.Answer
import com.sunday.appteoria.data.source.datastore.IDataStore
import com.sunday.appteoria.domain.repository.IRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataStore: IDataStore
): IRepository {

    override suspend fun getNameValue(key: String): Answer<String> {
        return dataStore.readNameValue(key)
    }

    override suspend fun setNameValue(key: String, value: String): Answer<String> {
        return dataStore.saveNameValue(key, value)
    }
}