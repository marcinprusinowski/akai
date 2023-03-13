import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

class MapperTest {


    @Test
    fun `boolean value`() {
        //given
        val json = buildMap<String, Any> {
            put("test-key", "true")
        }

        //when
        val result = map(json) {
            boolean("test-key")
        }

        //then
        assertTrue(
            result!!
        )

    }

    @Test
    fun `string value`() {
        //given
        val json = buildMap<String, Any> {
            put("test-key", "test-value")
        }

        //when
        val result = map(json) {
            string("test-key")
        }

        //then
        assertEquals(
            "test-value",
            result
        )
    }

    @Test
    fun `int value`() {
        //given
        val json = buildMap<String, Any> {
            put("test-key", "1")
        }

        //when
        val result = map(json) {
            int("test-key")
        }

        //then
        assertEquals(
            1,
            result
        )
    }

    @Test
    fun `double value`() {
        //given
        val json = buildMap<String, Any> {
            put("test-key", "1.0")
        }

        //when
        val result = map(json) {
            double("test-key")
        }

        //then
        assertEquals(
            1.0,
            result
        )
    }

    @Test
    fun `float value`() {
        //given
        val json = buildMap<String, Any> {
            put("test-key", "1")
        }

        //when
        val result = map(json) {
            float("test-key")
        }

        //then
        assertEquals(
            1f,
            result
        )
    }

    @Test
    fun `short value`() {
        //given
        val json = buildMap<String, Any> {
            put("test-key", "1")
        }

        //when
        val result = map(json) {
            short("test-key")
        }

        //then
        assertEquals(
            1,
            result
        )
    }


    @Test
    fun `accept ISO date only`() {
        // given
        val json = buildMap<String, Any> {
            put("date", "2017-12-31T23:59:59.999Z")
        }

        // when
        val result = map(json) {
            date(
                name = "date",
                required = true
            )
        }

        //then
        assertEquals(
            Instant.parse("2017-12-31T23:59:59.999Z"),
            result
        )
    }

    @Test
    fun `throws when RFC date`() {
        // given
        val json = buildMap<String, Any> {
            put("date", "Thu, 31 Dec 2017 23:59:59 +0000")
        }

        // when
        val exception = assertThrows(MappingException::class.java) {
            map(json) {
                date("date")
            }
        }

        // then
        assertEquals(
            listOf(
                InvalidFieldValueError(
                    field = "date",
                    value = "Thu, 31 Dec 2017 23:59:59 +0000",
                    type = MappingErrorType.INVALID_TYPE
                )
            ),
            exception!!.errors
        )
    }

    @Test
    fun `non-existing key`() {
        //given
        val json = buildMap<String, Any> {
            put("test-key", "true")
        }

        //when
        val exception = assertThrows(MappingException::class.javaObjectType) {
            map(json) {
                boolean("testkey")
            }
        }

        //then
        assertEquals(
            listOf(
                FieldNotFoundError(
                    name = "testkey",
                    type = MappingErrorType.NOT_FOUND
                )
            ),
            exception.errors
        )

    }

    @Test
    fun `invalid type`() {
        //given
        val json = buildMap<String, Any> {
            put("test-key", "1000")
        }

        //when
        val exception = assertThrows(MappingException::class.javaObjectType) {
            map(json) {
                boolean("test-key")
            }
        }

        //then
        assertEquals(
            listOf(
                InvalidFieldValueError(
                    field = "test-key",
                    value = "1000",
                    type = MappingErrorType.INVALID_TYPE
                )
            ),
            exception.errors
        )
    }
}