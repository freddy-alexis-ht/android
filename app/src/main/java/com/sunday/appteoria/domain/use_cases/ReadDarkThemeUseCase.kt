package com.sunday.appteoria.domain.use_cases

import com.sunday.appteoria.domain.repository.IRepository
import javax.inject.Inject

class ReadDarkThemeUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke(key: String): Boolean? {
        return repository.getDarkTheme(key)
    }
}