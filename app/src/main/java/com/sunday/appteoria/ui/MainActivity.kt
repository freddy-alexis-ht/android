package com.sunday.appteoria.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunday.appteoria.ui.theme.AppTeoriaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // another way (but color fixed)
            // this.window.statusBarColor = ContextCompat.getColor(this,R.color.green_900)
            val formVM = hiltViewModel<FormVM>()
            val context = LocalContext.current
            AppTeoriaTheme {
                // Theory says it has to be set in each activity
                window?.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
                FormScreen(formVM, context)
            }
        }
    }
}
