package com.sunday.appteoria.domain

import javax.inject.Inject

class ReadDarkThemeUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke(key: String): Boolean? {
        return repository.readDarkTheme(key)
    }
}