@file:JvmName("African Countries")

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*

fun main() = application {
    val icon = painterResource("africa.svg")
    Tray(icon)
    val state = rememberWindowState(position = WindowPosition(Alignment.Center))
    Window(onCloseRequest = ::exitApplication, title = "Africa", icon = icon, state = state) {
        App()
    }
}