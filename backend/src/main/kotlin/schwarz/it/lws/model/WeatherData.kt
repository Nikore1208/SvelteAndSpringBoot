package schwarz.it.lws.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "weather_data")
data class WeatherData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val city: String,

    @Column(nullable = false)
    val forecastDate: LocalDateTime,

    @Column(nullable = false)
    val temperature: Double,

    @Column(nullable = false)
    val minTemperature: Double,

    @Column(nullable = false)
    val maxTemperature: Double,

    @Column(nullable = false)
    val humidity: Int,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val iconCode: String,

    @Column(nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()
)
