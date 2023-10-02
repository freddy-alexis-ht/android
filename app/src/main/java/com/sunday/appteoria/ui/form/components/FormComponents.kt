package com.sunday.appteoria.ui.form.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import com.sunday.appteoria.R
import com.sunday.appteoria.util.UiText

@Composable
fun TextWelcome(nameStored: String) {
    Text(text = stringResource(id = R.string.form_text_welcome, nameStored) )
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

@Composable
fun TextFieldEmail(
    email: String,
    onChange: (String) -> Unit,
    emailError: UiText?,
    label: String = stringResource(id = R.string.form_text_field_email),
    localFocusManager: FocusManager = LocalFocusManager.current,
) {
   OutlinedTextField(
       value = email,
       onValueChange = { onChange(it) },
       label = {
           CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
               Text(text = label, style = MaterialTheme.typography.body2)
           }
       },
       keyboardOptions = KeyboardOptions(
           keyboardType = KeyboardType.Email,
           capitalization = KeyboardCapitalization.None,
           imeAction = ImeAction.Next
       ),
       keyboardActions = KeyboardActions(
           onNext = {localFocusManager.moveFocus(FocusDirection.Down)}
       )
   )
   emailError?.let { error -> TextFieldError(error = error) }
}

@Composable
fun TextFieldPhone(
    phone: String,
    onChange: (String) -> Unit,
    phoneError: UiText?,
    label: String = stringResource(id = R.string.form_text_field_phone),
    localFocusManager: FocusManager = LocalFocusManager.current,
) {
    OutlinedTextField(
        value = phone,
        onValueChange = { onChange(it) },
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            capitalization = KeyboardCapitalization.None,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {localFocusManager.moveFocus(FocusDirection.Down)}
        )
    )
    phoneError?.let { error -> TextFieldError(error = error) }
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
    onClick: () -> Unit,
    label: String = stringResource(id = R.string.form_button_send),
) {
    Button(onClick = { onClick() }) {
        Text(
            text = label,
            style = MaterialTheme.typography.button
        )
    }
}
