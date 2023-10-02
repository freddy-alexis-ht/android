package com.sunday.appteoria.ui.form

sealed class FormEvent {
    data class OnChangeName(val name: String): FormEvent()
    data class OnChangePassword(val password: String): FormEvent()
    data class OnPasswordVisibility(val showPassword: Boolean): FormEvent()
    object OnButtonClick: FormEvent()
}
