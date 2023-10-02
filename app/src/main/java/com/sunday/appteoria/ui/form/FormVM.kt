package com.sunday.appteoria.ui.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.appteoria.data.Answer
import com.sunday.appteoria.domain.use_cases.GetNameUseCase
import com.sunday.appteoria.domain.use_cases.InsertNameUseCase
import com.sunday.appteoria.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class FormVM @Inject constructor(
    private val insertNameUseCase: InsertNameUseCase,
    private val getNameUseCase: GetNameUseCase,
) : ViewModel() {

    var state by mutableStateOf(FormState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            getNameFromDataStore()
            delay(500L)
            state = state.copy(isLoading = false)
        }

    }

    private val uiEventChannel = Channel<UiEvent>()
    val uiEventFlow = uiEventChannel.receiveAsFlow()

    fun onEvent(event: FormEvent) {
        when (event) {
            is FormEvent.OnChangeName -> state = state.copy(name = event.name)
            is FormEvent.OnChangePassword -> state = state.copy(password = event.password)
            is FormEvent.OnButtonClick -> onButtonClick()
            is FormEvent.OnPasswordVisibility -> state =
                state.copy(showPassword = event.showPassword)
        }
    }

    private fun onButtonClick() {
        viewModelScope.launch {
            setNameIntoDataStore(state)
            getNameFromDataStore()
        }
    }

    private suspend fun setNameIntoDataStore(stateEntered: FormState) {
        insertNameUseCase(stateEntered)
            .also { answers ->
                val hasError = answers.any { it is Answer.Error }
                if (hasError) {
                    state = state.copy(
                        nameError = answers.component1().message,
                        passwordError = answers.component2().message)
                } else {
                    uiEventChannel.send(
                        UiEvent.ShowSnackBar(
                            message = answers.component1().message,
                        )
                    )
                    state = state.copy(
                        nameError = null,
                        passwordError = null
                    )
                }
            }
    }

    private suspend fun getNameFromDataStore() {
        getNameUseCase()
            .also { answer ->
                when (answer) {
                    is Answer.Error -> {
                        uiEventChannel.send(
                            UiEvent.ShowSnackBar(
                                message = answer.message
                            )
                        )
                    }
                    is Answer.Success -> {
                        state = state.copy(nameStored = answer.data as String)
                    }
                }
            }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: UiText?) : UiEvent()
    }
}

