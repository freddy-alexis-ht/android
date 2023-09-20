package com.sunday.appteoria.domain.use_cases

import com.sunday.appteoria.domain.repository.IRepository
import javax.inject.Inject

class SaveDarkThemeUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke(key: String, value: Boolean) {
        repository.setDarkTheme(key, value)
    }
}