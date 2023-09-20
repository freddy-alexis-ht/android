package com.sunday.appteoria.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunday.appteoria.ui.theme.AppTeoriaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nameVM = hiltViewModel<NameVM>()
            val context = LocalContext.current
            AppTeoriaTheme {
                NameScreen(nameVM, context)
            }
        }
    }
}
