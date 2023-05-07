@file:JvmName("AfricaApp")

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*
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
    val state = rememberWindowState(position = WindowPosition(Alignment.Center))
    Window(onCloseRequest = ::exitApplication, title = "Africa", icon = icon, state = state) {
        App(africa)
    }
}