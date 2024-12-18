package schwarz.it.lws.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "weather_data")
data class WeatherData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var city: String = "",

    @Column(nullable = false)
    var forecastDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var temperature: Double = 0.0,

    @Column(nullable = false)
    var minTemperature: Double = 0.0,

    @Column(nullable = false)
    var maxTemperature: Double = 0.0,

    @Column(nullable = false)
    var humidity: Int = 0,

    @Column(nullable = false)
    var description: String = "",

    @Column(nullable = false)
    var iconCode: String = "",

    @Column(nullable = false)
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now()
)



