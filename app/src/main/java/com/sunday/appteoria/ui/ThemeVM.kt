package com.sunday.appteoria.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.appteoria.domain.use_cases.ReadDarkThemeUseCase
import com.sunday.appteoria.domain.use_cases.SaveDarkThemeUseCase
import com.sunday.appteoria.util.KEY_DARK_THEME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeVM @Inject constructor(
    val readDarkThemeUseCase: ReadDarkThemeUseCase,
    val saveDarkThemeUseCase: SaveDarkThemeUseCase
): ViewModel() {

    var state by mutableStateOf(ThemeState())
        private set

    init {
        readDarkTheme()
    }
    fun onEvent(event: ThemeEvent) {
        when(event) {
            is ThemeEvent.OnToggle -> onToggle(event.isChecked)
            is ThemeEvent.SaveDarkThemeInDataStore -> saveDarkTheme(event.value)
        }
    }

    private fun onToggle(isChecked: Boolean) {
        state = state.copy(isDarkThemeEnabled = isChecked)
    }

    private fun saveDarkTheme(value: Boolean) {
        viewModelScope.launch {
            saveDarkThemeUseCase(KEY_DARK_THEME, value)
        }
    }

    private fun readDarkTheme() {
        viewModelScope.launch {
            readDarkThemeUseCase(KEY_DARK_THEME)?.let {
                state = state.copy(isDarkThemeEnabled = it)
            }
        }
    }
}