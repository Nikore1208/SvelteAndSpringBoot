package schwarz.it.lws.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import schwarz.it.lws.dateTimeUtils.DateTimeUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootTest
class DateTimeUtilsTest {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")

    @Test
    fun `isOlderThanOneHour should return true for timestamp older than three hours`() {

        val timestamp = LocalDateTime.now().minusHours(5)

        val result = DateTimeUtils.isOlderThanThreeHours(timestamp)

        assertTrue(result)
    }

    @Test
    fun `isOlderThanOneHour should return false for timestamp less than three hours`(){

        val timestamp = LocalDateTime.now().minusHours(2)

        val result = DateTimeUtils.isOlderThanThreeHours(timestamp)

        assertFalse(result)
    }

}