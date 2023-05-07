import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.File

private const val COUNTRIES_FILENAME = "africa.json"

internal fun readCountries(filename: String = COUNTRIES_FILENAME): Map<ISOCode, Country> {
    val url = Africa::class.java.classLoader.getResource(filename)
    val file = File(url.toURI())
    val json = file.readText()
    val serializer: KSerializer<Map<ISOCode, Country>> = serializer()
    return Json.decodeFromString(serializer, json)
}