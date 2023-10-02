package com.sunday.appteoria.domain.validation

import com.sunday.appteoria.data.Answer
import javax.inject.Inject
import com.sunday.appteoria.R
import com.sunday.appteoria.util.UiText

class ValidateName @Inject constructor() {

    operator fun invoke(name: String): Answer<String> {
        if(name.isBlank()) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_name_can_not_be_empty)
            )
        }
        return Answer.Success(name)
    }
}