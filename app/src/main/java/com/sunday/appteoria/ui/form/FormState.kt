package com.sunday.appteoria.ui.form

import com.sunday.appteoria.util.UiText

data class FormState(
    val name: String = "",
    val nameError: UiText? = null,
    val nameStored: String = "Amigo",
    val password: String = "",
    val showPassword: Boolean = false,
    val passwordError: UiText? = null,
    val isLoading: Boolean = false
)