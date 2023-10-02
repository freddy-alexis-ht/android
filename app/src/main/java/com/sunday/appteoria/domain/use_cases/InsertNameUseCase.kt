package com.sunday.appteoria.domain.use_cases

import com.sunday.appteoria.data.Answer
import com.sunday.appteoria.domain.repository.IRepository
import com.sunday.appteoria.domain.validation.ValidateName
import com.sunday.appteoria.domain.validation.ValidatePassword
import com.sunday.appteoria.ui.form.FormState
import com.sunday.appteoria.util.Constants
import javax.inject.Inject

class InsertNameUseCase @Inject constructor(
    private val repository: IRepository,
    private val validateName: ValidateName,
    private val validatePassword: ValidatePassword,
) {

    suspend operator fun invoke(state: FormState): List<Answer<String>> {
        val nameResult = validateName(state.name)
        val passwordResult = validatePassword(state.password)
        val list: List<Answer<String>> = listOf(nameResult, passwordResult)
        val hasError = list.any { it is Answer.Error }
        if (hasError)
            return list
        else
            return listOf(repository.setNameValue(Constants.KEY_NAME, state.name))
    }

}