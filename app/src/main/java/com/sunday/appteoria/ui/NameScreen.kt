package com.sunday.appteoria.ui

import android.content.Context
import android.health.connect.datatypes.BodyFatRecord
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.sunday.appteoria.util.UiText

@Composable
fun NameScreen(nameVM: NameVM, context: Context) {

    val state = nameVM.state
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    LaunchedEffect(scaffoldState.snackbarHostState) {
        nameVM.uiEventFlow.collect { event ->
            when (event) {
                is NameVM.UiEvent.ShowSnackBar -> event.message?.let {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.asString(context)
                    )
                }
            }
        }
    }
    Scaffold(scaffoldState = scaffoldState) {
        Column() {
            WelcomeText(
                nameStored = state.nameStored
            )
            InputText(
                name = state.name,
                onChange = { nameVM.onEvent(NameEvent.OnChangeName(it)) }
            )
            ErrorText(
                nameError = state.nameError
            )
            SubmitButton(
                name = state.name,
                onClick = { nameVM.onEvent(NameEvent.OnButtonClick(it)) }
            )
        }
    }
}

@Composable
fun WelcomeText(nameStored: String) {
    Text(text = "Hola, $nameStored")
}

@Composable
fun InputText(
    name: String,
    onChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = name,
        onValueChange = { onChange(it) },
        label = { Text(text = "Name") }
    )
}

@Composable
fun ErrorText(nameError: UiText?) {
    nameError?.let { error ->
        Text(text = error.asString())
    }
}

@Composable
fun SubmitButton(
    name: String,
    onClick: (String) -> Unit,
) {
    Button(onClick = { onClick(name) }) {
        Text(text = "Enviar")
    }
}
