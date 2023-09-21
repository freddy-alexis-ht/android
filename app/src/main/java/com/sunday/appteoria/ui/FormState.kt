package com.sunday.appteoria.ui

import com.sunday.appteoria.util.UiText

data class FormState(
    val name: String = "",
    val nameError: UiText? = null,
    val nameStored: String = "Amigo"
)