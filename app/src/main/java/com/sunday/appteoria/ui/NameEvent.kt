package com.sunday.appteoria.ui

sealed class NameEvent {
    data class OnChangeName(val name: String): NameEvent()
    data class OnButtonClick(val name: String): NameEvent()
}
