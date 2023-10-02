package com.sunday.appteoria.domain.validation

import com.sunday.appteoria.data.Answer
import javax.inject.Inject
import com.sunday.appteoria.R
import com.sunday.appteoria.util.Constants
import com.sunday.appteoria.util.UiText

class ValidatePhone @Inject constructor() {

    operator fun invoke(phone: String): Answer<String> {
        if (phone.isBlank()) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_phone_can_not_be_empty)
            )
        }
        if (phone.length != Constants.CORRECT_PHONE_LENGTH) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_phone_length_too_short,
                    Constants.CORRECT_PHONE_LENGTH)
            )
        }
        if (phone.first().toString() != Constants.CORRECT_PHONE_FIRST_NUMBER) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_phone_not_start_by_nine,
                    Constants.CORRECT_PHONE_FIRST_NUMBER)
            )
        }
        return Answer.Success(phone)
    }
}