package com.sunday.appteoria.domain.validation

import com.sunday.appteoria.data.Answer
import javax.inject.Inject
import com.sunday.appteoria.R
import com.sunday.appteoria.util.UiText

class ValidateHobbies @Inject constructor() {

    operator fun invoke(hobbies: List<String>): Answer<String> {
        if(hobbies.isEmpty()) {
            return Answer.Error(
                message = UiText.StringResource(R.string.validation_error_hobby_choose_one)
            )
        }
        return Answer.Success(hobbies.toString())
    }
}