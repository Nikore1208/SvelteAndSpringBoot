package schwarz.it.lws.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import schwarz.it.lws.model.WeatherData
import schwarz.it.lws.repository.WeatherRepository
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class WeatherService(val weatherRepository: WeatherRepository) {
    private val apiKey = "9eb6056c21bd7f742bff107b5794c961"
    private val apiUrl = "https://api.openweathermap.org/data/2.5/weather"

    fun saveWeatherInDatabase(city: String) {
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

        weatherRepository.save(weatherData)
    }

    fun getCurrentWeather(city: String): WeatherData {
        if (!weatherRepository.existsByCity(city) || isOlderThanOneHour(weatherRepository.findTopByCityOrderByForecastDateDesc(city).forecastDate.toString())) {
            saveWeatherInDatabase(city)
        }

        val forecastData = weatherRepository.findTopByCityOrderByForecastDateDesc(city)
        return forecastData

    }



    fun isOlderThanOneHour(timestamp: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")

        val parsedTimestamp = LocalDateTime.parse(timestamp, formatter)

        val currentTime = LocalDateTime.now()

        val duration = Duration.between(parsedTimestamp, currentTime)

        return duration.toHours() > 1
    }

}

