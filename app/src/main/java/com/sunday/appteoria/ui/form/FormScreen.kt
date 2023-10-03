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
import com.sunday.appteoria.ui.form.components.*

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
                TextFieldEmail(
                    email = state.email,
                    onChange = { formVM.onEvent(FormEvent.OnChangeEmail(it)) },
                    emailError = state.emailError
                )
                TextFieldPhone(
                    phone = state.phone,
                    onChange = { formVM.onEvent(FormEvent.OnChangePhone(it)) },
                    phoneError = state.phoneError
                )
                RadioButtonGroupGender(
                    genderList = listOf(
                        stringResource(R.string.form_radio_button_male),
                        stringResource(R.string.form_radio_button_female)
                    ),
                    onSelect = { formVM.onEvent(FormEvent.OnSelectGender(it))},
                    selectedGender = state.gender,
                    genderError = state.genderError
                )
                CheckBoxGroupHobby(
                    hobbyList = listOf(
                        stringResource(id = R.string.form_checkbox_sport),
                        stringResource(id = R.string.form_checkbox_music),
                        stringResource(id = R.string.form_checkbox_movies),
                        stringResource(id = R.string.form_checkbox_technology)
                    ),
                    onCheck = { formVM.onEvent(FormEvent.OnCheckHobby(it))},
                    hobbies = state.hobbies,
                    hobbyError = state.hobbyError
                )
                TextFieldPassword(
                    password = state.password,
                    onChange = { formVM.onEvent(FormEvent.OnChangePassword(it)) },
                    showPassword = state.showPassword,
                    onChangeVisibility = { formVM.onEvent(FormEvent.OnPasswordVisibility(it))},
                    passwordError = state.passwordError
                )
                ButtonSubmit(
                    onClick = { formVM.onEvent(FormEvent.OnButtonClick) }
                )
            }
        }
    }
}