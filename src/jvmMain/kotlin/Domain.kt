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


class Africa(private val countries: Countries) : Iterable<Country> {
    override fun iterator(): Iterator<Country> = CountryIterator(countries)

    private class CountryIterator(private val countries: Countries) : Iterator<Country> {
        private var nextIndex = if (countries.isEmpty()) -1 else 0

        override fun hasNext(): Boolean = nextIndex in countries.indices
        override fun next(): Country {
            if (!hasNext()) throw NoSuchElementException("No more countries")
            return countries[nextIndex].also {
                nextIndex += 1
            }
        }
    }
}

class ViewModel : CoroutineScope by MainScope() {
    val countryGroups = MutableStateFlow<CountryGroups>(emptyMap())

    init {
        launch(Dispatchers.IO) {
            val countries = readCountries()
                .sortedBy(Country::name)
            countryGroups.emit(Africa(countries)
                .groupCountriesByFirstChar())
        }
    }

    private fun Africa.groupCountriesByFirstChar(): CountryGroups =
        groupBy { country -> country.name.first().toString() }
}