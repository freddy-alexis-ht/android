package com.sunday.appteoria.ui.form

sealed class FormEvent {
    data class OnChangeName(val name: String): FormEvent()
    data class OnButtonClick(val name: String): FormEvent()
}
