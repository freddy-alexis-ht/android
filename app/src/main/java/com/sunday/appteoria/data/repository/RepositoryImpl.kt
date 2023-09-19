package com.sunday.appteoria.data.repository

import com.sunday.appteoria.data.source.preferences.IPreferences
import com.sunday.appteoria.domain.IRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val preferences: IPreferences
): IRepository {

    override suspend fun saveDarkTheme(key: String, value: Boolean) {
        preferences.saveDarkTheme(key, value)
    }

    override suspend fun readDarkTheme(key: String): Boolean? {
        return preferences.readDarkTheme(key)
    }
}