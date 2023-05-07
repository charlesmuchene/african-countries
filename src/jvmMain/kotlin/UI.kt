import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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