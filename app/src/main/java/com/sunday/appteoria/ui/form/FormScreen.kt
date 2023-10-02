package com.sunday.appteoria.ui.form

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import com.sunday.appteoria.util.UiText
import com.sunday.appteoria.R

@OptIn(ExperimentalComposeUiApi::class)
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
                TextFieldPassword(
                    password = state.password,
                    onChange = { formVM.onEvent(FormEvent.OnChangePassword(it)) },
                    showPassword = state.showPassword,
                    onChangeVisibility = { formVM.onEvent(FormEvent.OnPasswordVisibility(it))},
                    passwordError = state.passwordError
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldPassword(
    password: String,
    onChange: (String) -> Unit,
    onChangeVisibility: (Boolean) -> Unit,
    showPassword: Boolean,
    passwordError: UiText?,
    label: String = stringResource(id = R.string.form_text_field_password),
    localFocusManager: FocusManager = LocalFocusManager.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
) {
    OutlinedTextField(
        value = password,
        onValueChange = { onChange(it) },
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                localFocusManager.clearFocus()
                keyboardController?.hide()
            }
        ),
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { onChangeVisibility(false) }) {
                    Icon(imageVector = Icons.Filled.Visibility, contentDescription = stringResource(
                        id = R.string.form_text_field_password_hide_password
                    ))
                }
            } else {
                IconButton(onClick = { onChangeVisibility(true) }) {
                    Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = stringResource(
                        id = R.string.form_text_field_password_show_password
                    ))
                }
            }
        },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
    )
    passwordError?.let { error -> TextFieldError(error = error) }
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
            text = label,
            style = MaterialTheme.typography.button
        )
    }
}
