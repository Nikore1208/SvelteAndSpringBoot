package schwarz.it.lws.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import schwarz.it.lws.service.WeatherService

@SpringBootTest
class WeatherServiceTest {

    @Autowired
    private lateinit var weatherService: WeatherService

    @Test
    fun `isOlderThanOneHour should return true for timestamp older than one hour`() {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")

        val timestamp = LocalDateTime.now().minusHours(2).format(formatter)

        val result = weatherService.isOlderThanOneHour(timestamp)

        assertTrue(result)
    }

    @Test
    fun `isOlderThanOneHour should return false for timestamp less than one hour`(){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")

        val timestamp = LocalDateTime.now().minusMinutes(10).format(formatter)

        val result = weatherService.isOlderThanOneHour(timestamp)

        assertFalse(result)
    }

}