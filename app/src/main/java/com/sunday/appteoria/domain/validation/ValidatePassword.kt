package com.sunday.appteoria.domain.validation

import com.sunday.appteoria.R
import com.sunday.appteoria.data.Answer
import com.sunday.appteoria.util.Constants
import com.sunday.appteoria.util.UiText
import javax.inject.Inject

class ValidatePassword @Inject constructor() {

    operator fun invoke(password: String): Answer<String> {
        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
            return Answer.Error(
                message = UiText.StringResource(
                    R.string.validation_error_length_password_too_short,
                    Constants.MIN_PASSWORD_LENGTH
                )
            )
        }
        val containsLetterAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLetterAndDigits) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_not_contains_letters_and_digits)
            )
        }
        return Answer.Success(password)
    }
}