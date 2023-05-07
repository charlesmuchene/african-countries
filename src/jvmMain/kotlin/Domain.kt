import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

typealias ISOCode = String
typealias Countries = List<Country>
typealias CountryGroups = Map<String, Countries>

@Serializable
data class Country(
    val name: String,
    val capital: String,
    val flag: String,
    val lang: String
) {
    var code: ISOCode = ""
}


class Africa(private val countries: Countries = emptyList()) : Iterable<Country> {
    override fun iterator(): Iterator<Country> = CountryIterator(countries.iterator())

    private class CountryIterator(val iterator: Iterator<Country>) : Iterator<Country> {
        override fun hasNext(): Boolean = iterator.hasNext()
        override fun next(): Country = iterator.next()
    }
}

class ViewModel : CoroutineScope by MainScope() {
    val countryGroups = MutableStateFlow<CountryGroups>(emptyMap())

    init {
        launch(Dispatchers.IO) {
            val countries = readCountries()
            countryGroups.emit(Africa(countries).groupCountriesByFirstChar())
        }
    }

    private fun Africa.groupCountriesByFirstChar(): CountryGroups =
        groupBy { country -> country.name.first().toString() }
}