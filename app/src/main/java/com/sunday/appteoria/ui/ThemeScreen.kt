package com.sunday.appteoria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import com.sunday.appteoria.R

@Composable
fun ThemeScreen(viewModel: ThemeVM) {
    val state = viewModel.state
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LabelledSwitch(
                label = stringResource(id = R.string.dark_theme_enabled),
                isSwitchChecked = state.isDarkThemeEnabled,
                onToggle = { viewModel.onEvent(ThemeEvent.OnToggle(it))},
                saveDarkTheme = {viewModel.onEvent(ThemeEvent.SaveDarkThemeInDataStore(it))}
            )
        }
    }
}

@Composable
fun LabelledSwitch(
    label: String,
    isSwitchChecked: Boolean,
    onToggle: (Boolean) -> Unit,
    saveDarkTheme: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = isSwitchChecked,
                onValueChange = { // it: Boolean
                    onToggle(it)
                    saveDarkTheme(it)
                },
                role = Role.Switch
            )
    ) {
       Text(
           text = label,
           modifier = Modifier.align(Alignment.CenterStart)
       )
       Switch(
           checked = isSwitchChecked,
           onCheckedChange = null,
           modifier = Modifier.align(Alignment.CenterEnd)
       )
    }
}