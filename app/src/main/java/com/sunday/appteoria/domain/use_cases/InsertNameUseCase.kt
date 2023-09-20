package com.sunday.appteoria.domain.use_cases

import com.sunday.appteoria.data.Answer
import com.sunday.appteoria.domain.repository.IRepository
import com.sunday.appteoria.domain.validation.ValidateName
import com.sunday.appteoria.util.Constants
import javax.inject.Inject

class InsertNameUseCase @Inject constructor(
    private val repository: IRepository,
    private val validateName: ValidateName
) {

    suspend operator fun invoke(name: String): Answer<String> {
        val nameResult = validateEnteredValue(name)
        val hasError = listOf(nameResult).any { it is Answer.Error}
        if(hasError)
            return nameResult
        else
            return repository.setNameValue(Constants.KEY_NAME, name)
    }

    private fun validateEnteredValue(name: String): Answer<String> {
        return validateName(name)
    }

}