package com.sunday.appteoria.ui.form

import com.sunday.appteoria.util.UiText

data class FormState(
    val name: String = "",
    val nameError: UiText? = null,
    val nameStored: String = "Amigo",
    val email: String = "",
    val emailError: UiText? = null,
    val phone: String = "",
    val phoneError: UiText? = null,
    val gender: String? = null,
    val genderError: UiText? = null,
    val hobbies: MutableList<String> = arrayListOf(),
    val hobbyError: UiText? = null,
    val password: String = "",
    val showPassword: Boolean = false,
    val passwordError: UiText? = null,
    val isLoading: Boolean = false
) {
}