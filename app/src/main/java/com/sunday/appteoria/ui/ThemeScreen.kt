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
import androidx.compose.ui.unit.dp
import com.sunday.appteoria.R

@Composable
fun ThemeScreen(
    viewModel: ThemeViewModel,
) {
    val state = viewModel.state
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LabelledSwitch(
                isSwitchChecked = state.darkThemeValue,
                label = stringResource(id = R.string.dark_theme_enabled),
                onToggle = { viewModel.onEvent(ThemeEvent.ToggleDarkThemeValue(it))},
                saveDarkThemeValue = { viewModel.onEvent(ThemeEvent.SaveDarkThemeValue(it))}
            )
        }
    }
}

@Composable
fun LabelledSwitch(
    modifier: Modifier = Modifier,
    isSwitchChecked: Boolean,
    label: String,
    onToggle: (Boolean) -> Unit,
    saveDarkThemeValue: (Boolean) -> Unit,
) {
    Surface {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(36.dp)
                .toggleable(
                    value = isSwitchChecked,
                    onValueChange = {
                        onToggle(it)
                        saveDarkThemeValue(it)
                    },
                    role = Role.Switch,
                )
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = label,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(end = 16.dp)
            )
            Switch(
                checked = isSwitchChecked,
                onCheckedChange = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}