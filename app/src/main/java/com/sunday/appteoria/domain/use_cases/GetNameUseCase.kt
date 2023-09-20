package com.sunday.appteoria.domain.use_cases

import com.sunday.appteoria.data.Answer
import com.sunday.appteoria.domain.repository.IRepository
import com.sunday.appteoria.util.Constants
import javax.inject.Inject

class GetNameUseCase @Inject constructor(
    private val repository: IRepository
) {

    suspend operator fun invoke(): Answer<String> {
        return repository.getNameValue(Constants.KEY_NAME)
    }

}