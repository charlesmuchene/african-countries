import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun App() {
    val viewModel = remember { ViewModel() }
    val groups by viewModel.countryGroups.collectAsState()

    MaterialTheme {
        val modifier = Modifier.padding(horizontal = 12.dp)
        Box(modifier = modifier.fillMaxSize()) {
            val state = rememberLazyListState()
            CountryList(state, groups, modifier.fillMaxWidth())
            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(scrollState = state)
            )
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun CountryList(state: LazyListState, groups: CountryGroups, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        state = state
    ) {
        groups.forEach { (groupText, countries) ->
            stickyHeader(key = groupText) {
                CountryHeader(groupText, modifier)
            }

            val iterator = countries.iterator()
            while (iterator.hasNext()) {
                val country = iterator.next()
                item(key = country.code) {
                    CountryRow(country = country)
                }
            }
        }
    }
}

@Composable
private fun CountryRow(country: Country, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
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

@Composable
private fun CountryHeader(text: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = text)
        Spacer(modifier = Modifier.height(2.dp))
        Divider()
    }
}