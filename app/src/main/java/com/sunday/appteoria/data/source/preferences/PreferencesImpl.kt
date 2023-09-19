package com.sunday.appteoria.data.source.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

private const val PREFERENCES_NAME = "preferences_theme"
private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)
private const val TAG = "PreferencesImpl"
class PreferencesImpl @Inject constructor(
    private val context: Context
): IPreferences {

    override suspend fun saveDarkTheme(key: String, value: Boolean) {
        val dataStoreKey: Preferences.Key<Boolean> = booleanPreferencesKey(key)
        context.dataStore.edit { preferences -> // it: MutablePreferences
            preferences[dataStoreKey] = value
        }
    }

    override suspend fun readDarkTheme(key: String): Boolean? {
        val dataStoreKey: Preferences.Key<Boolean> = booleanPreferencesKey(key)
        var preferences: Preferences = context.dataStore.data
            .catch { exception ->
                if(exception is IOException) {
                    Log.e(TAG, "readDarkTheme: Error reading preferences", exception)
                    emit(emptyPreferences())
                }else{
                    exception.printStackTrace()
                }
            }
            .first()
        return preferences[dataStoreKey]
    }
}