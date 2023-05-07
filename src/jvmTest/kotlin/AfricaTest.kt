import junit.framework.TestCase.*
import org.junit.Test

class AfricaTest {

    private lateinit var africa: Africa

    @Test
    fun `empty country list does not have a next value`() {
        africa = Africa(emptyList())

        val iterator = africa.iterator()

        assertFalse(iterator.hasNext())
    }

    @Test(expected = NoSuchElementException::class)
    fun `empty country list throws an exception when requested for next value`() {
        africa = Africa(emptyList())

        val iterator = africa.iterator()

        iterator.next()
    }

    @Test
    fun `a single country list yields the sole country`() {
        val kenya = Country(name = "Kenya", lang = "English", capital = "Nairobi", flag = "🇰🇪")
        val countries = readCountries(filename = "test-africa.json")
        africa = Africa(countries)

        val iterator = africa.iterator()

        assertTrue(iterator.hasNext())
        val country = iterator.next()

        assertFalse(iterator.hasNext())
        assertEquals(country, kenya)
    }
}