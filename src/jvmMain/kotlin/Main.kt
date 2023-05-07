import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.File

@Composable
@Preview
fun App(africa: Africa) {
    MaterialTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            val iterator = africa.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                item(key = entry.code) {
                    CountryRow(entry.country)
                }
            }
        }
    }
}

@Composable
fun CountryRow(country: Country) {
    Text(country.name)
}

fun main() = application {
    var africa by remember { mutableStateOf(Africa()) }

    LaunchedEffect(Unit) {
        africa = Africa(readCountries())
    }

    Window(onCloseRequest = ::exitApplication, title = "Africa") {
        App(africa)
    }
}

private const val COUNTRIES_FILENAME = "africa.json"

fun readCountries(filename: String = COUNTRIES_FILENAME): Map<ISOCode, Country> {
    val url = Africa::class.java.classLoader.getResource(filename)
    val file = File(url.toURI())
    val json = file.readText()
    val serializer: KSerializer<Map<ISOCode, Country>> = serializer()
    return Json.decodeFromString(serializer, json)
}