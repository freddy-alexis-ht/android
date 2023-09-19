package com.sunday.appteoria.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.appteoria.domain.ReadDarkThemeUseCase
import com.sunday.appteoria.domain.SaveDarkThemeUseCase
import com.sunday.appteoria.util.DARK_THEME_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.reflect.Constructor
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor (
    private val saveDarkThemeUseCase: SaveDarkThemeUseCase,
    private val readDarkThemeUseCase: ReadDarkThemeUseCase
) : ViewModel() {

    var state by mutableStateOf(ThemeState())
        private set

    init { // get DataStore info
        readDarkTheme()
    }

    fun onEvent(event: ThemeEvent) {
        when(event) {
            is ThemeEvent.SaveDarkThemeValue -> saveDarkTheme(state.darkThemeValue)
            is ThemeEvent.ToggleDarkThemeValue -> state = state.copy(darkThemeValue = event.value)
        }
    }

    private fun saveDarkTheme(value: Boolean) {
        viewModelScope.launch {
            saveDarkThemeUseCase(DARK_THEME_KEY, value)
        }
    }
    private fun readDarkTheme() {
        viewModelScope.launch {
            readDarkThemeUseCase(DARK_THEME_KEY)?.let {
                state = state.copy(darkThemeValue = it)
            }
        }
    }
}