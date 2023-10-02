package com.sunday.appteoria.ui.form

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
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
                TextWelcome(
                    nameStored = state.nameStored
                )
                TextFieldName(
                    name = state.name,
                    onChange = { formVM.onEvent(FormEvent.OnChangeName(it)) },
                    nameError = state.nameError
                )
                ButtonSubmit(
                    name = state.name,
                    onClick = { formVM.onEvent(FormEvent.OnButtonClick(it)) }
                )
            }
        }
    }
}

@Composable
fun TextWelcome(nameStored: String) {
    Text(text = stringResource(id = R.string.form_welcome_text, nameStored) )
}

@Composable
fun TextFieldName(
    name: String,
    onChange: (String) -> Unit,
    nameError: UiText?,
    label: String = stringResource(id = R.string.form_text_field_name),
    localFocusManager: FocusManager = LocalFocusManager.current,
) {
    OutlinedTextField(
        value = name,
        onValueChange = { onChange(it) },
//        label = { Text(text = label) }
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {localFocusManager.moveFocus(FocusDirection.Down)}
        )
    )
    nameError?.let { error -> TextFieldError(error = error)}
}

@Composable
fun TextFieldError(error: UiText?) {
    error?.let { error ->
        Text(
            text = error.asString(),
            color = MaterialTheme.colors.onError,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun ButtonSubmit(
    name: String,
    onClick: (String) -> Unit,
    label: String = stringResource(id = R.string.form_button_send),
) {
    Button(onClick = { onClick(name) }) {
        Text(
            text = "Enviar",
            style = MaterialTheme.typography.button
        )
    }
}
