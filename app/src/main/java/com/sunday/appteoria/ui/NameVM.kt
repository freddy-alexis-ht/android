package com.sunday.appteoria.ui

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
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class NameVM @Inject constructor(
    private val insertNameUseCase: InsertNameUseCase,
    private val getNameUseCase: GetNameUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch { getNameFromDataStore() }
    }

    var state by mutableStateOf(NameState())
        private set

    private val uiEventChannel = Channel<UiEvent>()
    val uiEventFlow = uiEventChannel.receiveAsFlow()

    fun onEvent(event: NameEvent) {
        when (event) {
            is NameEvent.OnButtonClick -> onButtonClick(event.name)
            is NameEvent.OnChangeName -> state = state.copy(name = event.name)
        }
    }

    private fun onButtonClick(name: String) {
        viewModelScope.launch {
            setNameIntoDataStore(name)
            getNameFromDataStore()
        }
    }

    private suspend fun setNameIntoDataStore(name: String) {
        insertNameUseCase(name)
            .also { answer ->
                when (answer) {
                    is Answer.Error -> {
                        state = state.copy(nameError = answer.message)
                    }
                    is Answer.Success -> {
                        uiEventChannel.send(
                            UiEvent.ShowSnackBar(
                                message = answer.message
                            )
                        )
                    }
                    is Answer.Loading -> TODO()
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
                    is Answer.Loading -> TODO()
                }
            }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: UiText?) : UiEvent()
    }
}
