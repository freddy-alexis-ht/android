package com.sunday.appteoria.ui.form

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sunday.appteoria.util.UiText
import com.sunday.appteoria.R

@Composable
fun FormScreen(formVM: FormVM, context: Context) {

    val state = formVM.state
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    LaunchedEffect(scaffoldState.snackbarHostState) {
        formVM.uiEventFlow.collect { event ->
            when (event) {
                is FormVM.UiEvent.ShowSnackBar -> event.message?.let {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.asString(context)
                    )
                }
            }
        }
    }

    if(state.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else{
        Scaffold(scaffoldState = scaffoldState) {
            Column() {
                WelcomeText(
                    nameStored = state.nameStored
                )
                InputText(
                    name = state.name,
                    onChange = { formVM.onEvent(FormEvent.OnChangeName(it)) }
                )
                ErrorText(
                    nameError = state.nameError
                )
                SubmitButton(
                    name = state.name,
                    onClick = { formVM.onEvent(FormEvent.OnButtonClick(it)) }
                )
            }
        }
    }
}

@Composable
fun WelcomeText(nameStored: String) {
    Text(text = stringResource(id = R.string.form_welcome_text, nameStored) )
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
        Text(text = error.asString(), color = MaterialTheme.colors.onError)
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
