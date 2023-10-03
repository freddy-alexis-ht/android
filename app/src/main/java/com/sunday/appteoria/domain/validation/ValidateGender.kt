package com.sunday.appteoria.domain.validation

import com.sunday.appteoria.data.Answer
import javax.inject.Inject
import com.sunday.appteoria.R
import com.sunday.appteoria.util.UiText

class ValidateGender @Inject constructor() {

    operator fun invoke(gender: String): Answer<String> {
        if(gender.isBlank()) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_gender_can_not_be_empty)
            )
        }
        return Answer.Success(gender)
    }
}