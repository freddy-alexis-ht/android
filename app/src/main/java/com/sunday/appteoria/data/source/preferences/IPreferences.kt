package com.sunday.appteoria.data.source.preferences

interface IPreferences {
    suspend fun saveDarkTheme(key: String, value: Boolean)
    suspend fun readDarkTheme(key: String): Boolean?
}