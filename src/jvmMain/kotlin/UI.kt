import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun App(africa: Africa) {
    MaterialTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            val iterator = africa.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                item(key = entry.code) {
                    CountryRow(country = entry.country)
                }
            }
        }
    }
}

@Composable
fun CountryRow(country: Country, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(country.flag, fontSize = 100.sp)
        Spacer(modifier = Modifier.padding(horizontal = 18.dp))
        Column(modifier = Modifier, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(country.name, fontWeight = FontWeight.Bold, fontSize = 32.sp)
            Text(country.capital, fontWeight = FontWeight.Normal, fontSize = 24.sp)
            Text(country.lang, fontWeight = FontWeight.Normal, fontSize = 18.sp, color = Color.Gray)
        }
    }
}