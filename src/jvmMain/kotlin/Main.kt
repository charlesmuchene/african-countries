@file:JvmName("AfricaApp")

import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun main() = application {
    var africa by remember { mutableStateOf(Africa()) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            africa = Africa(readCountries())
        }
    }
    val icon = painterResource("africa.svg")
    Tray(icon)
    Window(onCloseRequest = ::exitApplication, title = "Africa", icon = icon) {
        App(africa)
    }
}