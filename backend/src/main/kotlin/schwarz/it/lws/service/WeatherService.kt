package schwarz.it.lws.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import schwarz.it.lws.model.WeatherData
import schwarz.it.lws.repository.WeatherRepository
import java.time.LocalDateTime

@Service
class WeatherService(val weatherRepository: WeatherRepository) {
    private val apiKey = "9eb6056c21bd7f742bff107b5794c961"
    private val apiUrl = "https://api.openweathermap.org/data/2.5/weather"

    fun saveWeather(city: String): WeatherData {
        val restTemplate = RestTemplate()
        val url = "$apiUrl?q=$city&appid=$apiKey&units=metric&lang=de"

        val response: Map<String, Any> = restTemplate.getForObject(url)

        val mainData = response["main"] as Map<*, *>
        val weatherInfo = (response["weather"] as List<Map<String, Any>>).first()

        val weatherData = WeatherData(
            city = city,
            forecastDate = LocalDateTime.now(),
            description = weatherInfo["description"] as String,
            temperature = mainData["temp"] as Double,
            humidity = mainData["humidity"] as Int,
            iconCode = weatherInfo["icon"] as String,
            minTemperature = mainData["temp_min"] as Double,
            maxTemperature = mainData["temp_max"] as Double
        )
        return weatherRepository.save(weatherData)
    }

    fun getForecast(city: String, days: Int): List<WeatherData> {


        saveWeather(city)
        val forecastData = weatherRepository.findByCity(city)
        return forecastData

    }
}

