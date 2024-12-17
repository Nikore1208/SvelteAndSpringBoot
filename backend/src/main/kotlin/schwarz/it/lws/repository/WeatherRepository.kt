package schwarz.it.lws.repository

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import schwarz.it.lws.model.WeatherData

@Repository
interface WeatherRepository : JpaRepository<WeatherData, Long> {

    fun findTopByCityOrderByForecastDateDesc(city: String): WeatherData

    fun existsByCity(city: String): Boolean

    @Transactional
    fun deleteByCity(city: String)

    fun findAllByCity(city: String): List<WeatherData>


}