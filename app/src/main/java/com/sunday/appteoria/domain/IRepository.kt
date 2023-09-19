package com.sunday.appteoria.domain

interface IRepository {
    suspend fun saveDarkTheme(key: String, value: Boolean)
    suspend fun readDarkTheme(key: String): Boolean?
}