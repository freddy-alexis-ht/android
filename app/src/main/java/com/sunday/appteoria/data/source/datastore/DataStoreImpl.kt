package com.sunday.appteoria.data.source.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import com.sunday.appteoria.R
import com.sunday.appteoria.data.Answer
import com.sunday.appteoria.util.UiText

private const val DATASTORE_NAME = "datastore_name"
private val Context.dataStore by preferencesDataStore(DATASTORE_NAME)

class DataStoreImpl @Inject constructor(
    private val context: Context,
) : IDataStore {

    override suspend fun saveNameValue(key: String, value: String): Answer<String> {
        val key1: Preferences.Key<String> = stringPreferencesKey(key)
        try {
            context.dataStore.edit { preferences ->
                preferences[key1] = value
            }
        } catch (exception: Exception) {
            return Answer.Error(UiText.StringResource(R.string.error_on_value_saving))
        }
        return Answer.Success(data = value, UiText.StringResource(R.string.success_on_value_saving))
    }

    override suspend fun readNameValue(key: String): Answer<String> {
        val key1: Preferences.Key<String> = stringPreferencesKey(key)
        val value: String? = try {
            context.dataStore.data.first()[key1]
        } catch (exception: Exception) {
            return Answer.Error(UiText.StringResource(R.string.error_on_value_reading))
        }
        return Answer.Success(value, UiText.StringResource(R.string.success_on_value_reading))
    }
}