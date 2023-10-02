package com.sunday.appteoria.domain.validation

import com.sunday.appteoria.data.Answer
import javax.inject.Inject
import com.sunday.appteoria.R
import com.sunday.appteoria.util.Constants
import com.sunday.appteoria.util.UiText
import java.util.regex.Pattern

class ValidateEmail @Inject constructor() {

    operator fun invoke(email: String): Answer<String> {
        if(email.isBlank()) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_email_can_not_be_empty)
            )
        }
        if (!Pattern.matches(Constants.EMAIL_VALIDATION_REGEX, email)) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_email_invalid)
            )
        }
        return Answer.Success(email)
    }
}