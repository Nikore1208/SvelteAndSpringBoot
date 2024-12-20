package schwarz.it.lws.model

import jakarta.persistence.Column
import java.time.LocalDate

data class ResponseWeatherData(

    @Column(nullable = false)
    var date: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    var temperature: Any = 0.0,    //XX

    @Column(nullable = false)
    var minTemperature: Double = 0.0,   //XX

    @Column(nullable = false)
    var maxTemperature: Double = 0.0,  //XX

    @Column(nullable = false)
    var humidity: Int = 0,           //XXX

    @Column(nullable = false)
    var description: String = "",      //XX

    @Column(nullable = false)
    var iconCode: String = "",         //XX

)


