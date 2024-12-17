package schwarz.it.lws.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

data class ResponseWeatherData(

    @Column(nullable = false)
    val temperature: Double,    //XX

    @Column(nullable = false)
    val minTemperature: Double,   //XX

    @Column(nullable = false)
    val maxTemperature: Double,  //XX

    @Column(nullable = false)
    val humidity: Int,           //XXX

    @Column(nullable = false)
    val description: String,      //XX

    @Column(nullable = false)
    val iconCode: String,         //XX

)
