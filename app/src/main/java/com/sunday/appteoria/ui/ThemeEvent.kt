package com.sunday.appteoria.ui

sealed class ThemeEvent {
    data class OnToggle(val isChecked: Boolean): ThemeEvent()
    data class SaveDarkThemeInDataStore(val value: Boolean): ThemeEvent()
}
