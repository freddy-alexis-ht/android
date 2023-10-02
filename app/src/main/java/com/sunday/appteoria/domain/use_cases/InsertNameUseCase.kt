package com.sunday.appteoria.domain.use_cases

import com.sunday.appteoria.data.Answer
import com.sunday.appteoria.domain.repository.IRepository
import com.sunday.appteoria.domain.validation.ValidateEmail
import com.sunday.appteoria.domain.validation.ValidateName
import com.sunday.appteoria.domain.validation.ValidatePassword
import com.sunday.appteoria.domain.validation.ValidatePhone
import com.sunday.appteoria.ui.form.FormState
import com.sunday.appteoria.util.Constants
import javax.inject.Inject

class InsertNameUseCase @Inject constructor(
    private val repository: IRepository,
    private val validateName: ValidateName,
    private val validateEmail: ValidateEmail,
    private val validatePhone: ValidatePhone,
    private val validatePassword: ValidatePassword,
) {

    suspend operator fun invoke(state: FormState): List<Answer<String>> {
        val listOfAnswers: List<Answer<String>> = listOf(
            validateName(state.name),
            validateEmail(state.email),
            validatePhone(state.phone),
            validatePassword(state.password)
        )
        val hasError = listOfAnswers.any { it is Answer.Error }
        if (hasError)
            return listOfAnswers
        else
            return listOf(repository.setNameValue(Constants.KEY_NAME, state.name))
    }

}