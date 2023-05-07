import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val capital: String,
    val flag: String,
    val lang: String
)

typealias ISOCode = String

class Africa(private val map: Map<ISOCode, Country> = emptyMap()) : Iterable<Africa.Entry> {
    data class Entry(val code: ISOCode, val country: Country)

    override fun iterator(): Iterator<Entry> = CountryIterator(map.entries.iterator())

    private class CountryIterator(val iterator: Iterator<Map.Entry<ISOCode, Country>>) : Iterator<Entry> {
        override fun hasNext(): Boolean = iterator.hasNext()
        override fun next(): Entry = iterator.next().toEntry()
        private fun Map.Entry<ISOCode, Country>.toEntry(): Entry = Entry(key, value)
    }

}