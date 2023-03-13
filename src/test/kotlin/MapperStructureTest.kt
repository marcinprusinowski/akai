import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant


class MapperStructureTest {


    @Test
    fun `flat structure`() {
        data class FlatTestClass(
            val testString: String,
            val testBoolean: Boolean,
            val testDate: Instant,
        )
        // given
        val json = buildMap<String, Any> {
            put("testString", "text")
            put("testBoolean", "false")
            put("testDate", "2017-12-31T23:59:59.999Z")
        }

        // when
        val result = map(json) {
            FlatTestClass(
                testString = string("testString")!!,
                testBoolean = boolean("testBoolean")!!,
                testDate = date("testDate")!!
            )
        }

        // then
        with(result) {
            Assertions.assertEquals(
                "text",
                testString
            )

            Assertions.assertFalse(
                testBoolean
            )

            Assertions.assertEquals(
                Instant.parse("2017-12-31T23:59:59.999Z"),
                testDate
            )
        }
    }


    @Test
    fun `flat nullable structure`() {
        data class FlatNullableTestClass(
            val testString: String,
            val testBoolean: Boolean,
            val testDate: Instant?,
        )
        // given
        val json = buildMap<String, Any> {
            put("testString", "text")
            put("testBoolean", "false")
        }

        // when
        val result = map(json) {
            FlatNullableTestClass(
                testString = string("testString")!!,
                testBoolean = boolean("testBoolean")!!,
                testDate = date(
                    name = "testDate",
                    required = false
                )
            )
        }

        // then
        with(result) {
            Assertions.assertEquals(
                "text",
                testString
            )

            Assertions.assertFalse(
                testBoolean
            )

            Assertions.assertNull(
                testDate
            )
        }
    }


    @Test
    fun `nested structure`() {
        data class TestClass(
            val testString: String,
        )

        data class NestedTestClass(
            val testString: String,
            val testBoolean: Boolean,
            val testObj: TestClass,
        )

        // given
        val json = buildMap {
            put("testString", "text")
            put("testBoolean", "false")
            put("testObj",
                buildMap<String, Any> {
                    put("testString", "nestedValue")
                }
            )
        }

        // when
        val result = map(json) {
            NestedTestClass(
                testBoolean = boolean("testBoolean")!!,
                testString = string("testString")!!,
                testObj = obj("testObj") {
                    TestClass(
                        testString = string("testString")!!
                    )
                }
            )
        }

        // then
        with(result) {
            Assertions.assertEquals(
                "text",
                testString
            )

            Assertions.assertFalse(
                testBoolean
            )

            Assertions.assertEquals(
                "nestedValue",
                testObj.testString
            )
        }
    }
}
