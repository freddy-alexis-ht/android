package com.sunday.appteoria.data.source.datastore

interface IDataStore {
    suspend fun saveDarkTheme(key: String, value: Boolean)
    suspend fun readDarkTheme(key: String): Boolean?
}