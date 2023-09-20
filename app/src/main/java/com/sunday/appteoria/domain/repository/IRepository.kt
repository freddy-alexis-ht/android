package com.sunday.appteoria.domain.repository

interface IRepository {
    suspend fun setDarkTheme(key: String, value: Boolean)
    suspend fun getDarkTheme(key: String): Boolean?
}