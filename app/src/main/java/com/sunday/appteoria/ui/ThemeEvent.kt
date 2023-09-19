package com.sunday.appteoria.ui

sealed class ThemeEvent {
    data class SaveDarkThemeValue(val value: Boolean?): ThemeEvent()
    data class ToggleDarkThemeValue(val value: Boolean): ThemeEvent()
}