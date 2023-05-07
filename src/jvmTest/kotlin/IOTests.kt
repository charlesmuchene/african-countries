import junit.framework.TestCase.assertEquals
import org.junit.Test

class IOTests {

    @Test
    fun `africa has 54 countries`() {
        val countries = readCountries()
        assertEquals(54, countries.size)
    }

}