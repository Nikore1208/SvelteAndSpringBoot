package schwarz.it.lws.dateTimeUtils

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DateTimeUtils {
    companion object {

        fun isOlderThanThreeHours(timestamp: LocalDateTime): Boolean {

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")

            val currentTime = LocalDateTime.now()

            val duration = Duration.between(timestamp, currentTime)

            return duration.toHours() > 3
        }

    }
}