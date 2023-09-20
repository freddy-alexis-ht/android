package com.sunday.appteoria.data.source.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

private const val PREFERENCES_NAME = "preferences_name"
private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)
private const val TAG = "DataStoreImpl"

class DataStoreImpl @Inject constructor(
    private val context: Context
): IDataStore {

    override suspend fun saveDarkTheme(key: String, value: Boolean) {
        val key1: Preferences.Key<Boolean> = booleanPreferencesKey(key)
        context.dataStore.edit { prefs ->
            prefs[key1] = value
        }
    }

    override suspend fun readDarkTheme(key: String): Boolean? {
        val key1: Preferences.Key<Boolean> = booleanPreferencesKey(key)
        val prefs: Preferences = context.dataStore.data
            .catch { exception ->
                if(exception is IOException) {
                    Log.e(TAG, "readDarkTheme: error reading prefs", exception)
                    emit(emptyPreferences())
                }else{
                    exception.printStackTrace()
                }
            }
            .first()
        return prefs[key1]
    }
}