package com.sunday.appteoria.ui.form

import com.sunday.appteoria.util.UiText

data class FormState(
    val name: String = "",
    val nameError: UiText? = null,
    val nameStored: String = "Amigo",
    val isLoading: Boolean = false
)