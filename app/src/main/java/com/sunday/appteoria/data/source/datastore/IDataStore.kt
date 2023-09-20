package com.sunday.appteoria.data.source.datastore

import com.sunday.appteoria.data.Answer

interface IDataStore {
    suspend fun saveNameValue(key: String, value: String): Answer<String>
    suspend fun readNameValue(key: String): Answer<String>
}