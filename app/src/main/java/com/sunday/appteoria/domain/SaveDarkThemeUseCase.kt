package com.sunday.appteoria.domain

import javax.inject.Inject

class SaveDarkThemeUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke(key: String, value: Boolean) {
        repository.saveDarkTheme(key, value)
    }
}