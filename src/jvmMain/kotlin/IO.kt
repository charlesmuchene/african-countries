import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

private const val COUNTRIES_FILENAME = "africa.json"

internal fun readCountries(filename: String = COUNTRIES_FILENAME): List<Country> =
    Africa::class.java.classLoader.getResourceAsStream(filename).use { stream ->
        val json = stream.bufferedReader().readText()
        val serializer: KSerializer<Map<ISOCode, Country>> = serializer()
        Json.decodeFromString(serializer, json)
            .map { entry ->
                entry.value.also { it.code = entry.key }
            }
    }