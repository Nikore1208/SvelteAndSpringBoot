package schwarz.it.lws.repository

import schwarz.it.lws.model.WeatherData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface WeatherRepository : JpaRepository<WeatherData, Long> {

    fun findTopByCityOrderByForecastDateDesc(city: String): WeatherData

    fun existsByCity(city: String): Boolean

    /*fun findByCityAndForecastDataBetween(
        city: String,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): List<WeatherData>*/

}