package com.sunday.appteoria.domain.repository
import com.sunday.appteoria.data.Answer

interface IRepository {
    suspend fun getNameValue(key: String): Answer<String>
    suspend fun setNameValue(key: String, value: String): Answer<String>
}