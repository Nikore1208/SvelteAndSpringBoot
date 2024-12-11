package schwarz.it.lws.repository

import schwarz.it.lws.model.WeatherData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface WeatherRepository : JpaRepository<WeatherData, Long> {

    /*fun findByCityAndForecastDataBetween(
        city: String,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): List<WeatherData>*/

}