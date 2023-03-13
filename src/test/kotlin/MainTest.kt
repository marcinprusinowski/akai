import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun `return credit user response`() {
        // given
        val pidToBeFetch = "4LC4A831"

        // when
        val result = findByPid(pidToBeFetch)

        // then
        with(result){
            assertEquals(
                "4LC4A831",
                pid
            )

            assertEquals(
                "Cheryl",
                name
            )

            assertEquals(
                "Bryant",
                surname
            )

            assertEquals(
                "9dcfeb64-8f7a-4a62-ad78-8dacf0c45d9f",
                id
            )
        }
    }

}