package com.sunday.appteoria.data

import com.sunday.appteoria.util.UiText

sealed class Answer<T>(
    val data: T? = null,
    val message: UiText? = null
) {
    class Success<T>(
        data: T? = null, message: UiText? = null
    ): Answer<T>(data = data, message = message)

    class Error<T>(
        message: UiText? = null
    ): Answer<T>(message = message)

//    class Loading<T>(
//        data: T? = null
//    ): Answer<T>(data)
}
