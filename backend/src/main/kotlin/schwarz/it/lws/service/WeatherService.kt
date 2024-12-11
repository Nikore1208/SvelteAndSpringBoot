package schwarz.it.lws.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import schwarz.it.lws.model.WeatherData
import java.time.LocalDateTime

@Service
class WeatherService {

    fun getForecast(city: String, days: Int) : List<WeatherData> {

        val forecastData = listOf(TESTWEATHERDATA)
        return forecastData

    }
}


val TESTWEATHERDATA = WeatherData(
    city = "city",
    forecastDate = LocalDateTime.now(), // Aktuelles Datum und Uhrzeit
    temperature = 20.0,  // Beispieltemperatur
    minTemperature = 15.0,  // Beispielminimum-Temperatur
    maxTemperature = 25.0,  // Beispielmaximum-Temperatur
    humidity = 60,  // Beispiel-Humidity-Wert
    description = "Clear sky",  // Beispielwetterbeschreibung
    iconCode = "01d"  // Beispiel-Icon-Code
)