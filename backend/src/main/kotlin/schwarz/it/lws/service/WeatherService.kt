package schwarz.it.lws.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import schwarz.it.lws.dateTimeUtils.DateTimeUtils
import schwarz.it.lws.model.ResponseWeatherData
import schwarz.it.lws.model.WeatherData
import schwarz.it.lws.repository.WeatherRepository
import java.time.LocalDateTime

@Service
class WeatherService(val weatherRepository: WeatherRepository) {
    private val apiKey = "9eb6056c21bd7f742bff107b5794c961"
    private val apiUrl = "https://api.openweathermap.org/data/2.5/forecast"


    fun saveWeatherInDatabase(city: String) {
        val restTemplate = RestTemplate()
        val url = "$apiUrl?q=$city&appid=$apiKey&units=metric&lang=de"

        val response: Map<String, Any> = restTemplate.getForObject(url)
        val forecasts = response["list"] as List<Map<String, Any>>

        for (i in 0 until (40)) {
            val forecast = forecasts[i]
            val mainData = forecast["main"] as Map<String, Any>
            val weatherInfo = (forecast["weather"] as List<Map<String, Any>>).first()

            val forecastDateTime = forecast["dt_txt"] as String?

            val weatherData = WeatherData(
                city = city,
                forecastDate = LocalDateTime.parse(forecastDateTime!!.replace(" ", "T")), // Datum und Uhrzeit
                description = weatherInfo["description"] as String,
                temperature = mainData["temp"] as Double,
                humidity = mainData["humidity"] as Int,
                iconCode = weatherInfo["icon"] as String,
                minTemperature = mainData["temp_min"] as Double,
                maxTemperature = mainData["temp_max"] as Double
            )

            // Speichern des Wetterdatensatzes in der Datenbank
            weatherRepository.save(weatherData)
        }
    }

    fun getWeatherForecast(city: String, days: Int): List<ResponseWeatherData> {
        if (!weatherRepository.existsByCity(city) || DateTimeUtils.isOlderThanThreeHours(weatherRepository.findTopByCityOrderByForecastDateDesc(city).forecastDate)) {
            weatherRepository.deleteByCity(city)
            saveWeatherInDatabase(city)
        }

        val forecastData = calculateWeatherForecast(city, days)
        return forecastData

    }

    fun calculateWeatherForecast(city: String, days: Int): List<ResponseWeatherData> {

        var weatherList = weatherRepository.findAllByCity(city)

        val weatherListPerDay = weatherList
            .groupBy { it.forecastDate.toLocalDate() }
            .map { (date, entries) ->
                ResponseWeatherData(
                    date = date,
                    description = "Durchschnittswerte - TODO",
                    temperature = Math.round(entries.map { it.temperature }.average() * 100) / 100.0,
                    minTemperature = entries.map {it.temperature }.min(),
                    maxTemperature = entries.map {it.temperature }.max(),
                    humidity = entries.map {it.humidity}.average().toInt(),
                    iconCode = "TODO",
                )
            }


        return weatherListPerDay.take(days)
    }




}

