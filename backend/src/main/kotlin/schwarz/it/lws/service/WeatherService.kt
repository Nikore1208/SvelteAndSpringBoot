package schwarz.it.lws.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import schwarz.it.lws.dateTimeUtils.DateTimeUtils
import schwarz.it.lws.model.ResponseWeatherData
import schwarz.it.lws.model.WeatherData
import schwarz.it.lws.repository.WeatherRepository
import java.time.LocalDateTime

@Service
class WeatherService(
    val weatherRepository: WeatherRepository,
    @Value("\${weather.api.url}") private val apiUrl: String,
    @Value("\${weather.api.key}") private val apiKey: String
) {


    fun saveWeatherForecasts(city: String) {
        val response = fetchWeatherData(city)
        val forecasts = extractForecasts(response)
        saveWeatherInDatabase(city, forecasts)
    }

    fun extractForecasts(response: Map<String, Any>): List<Map<String, Any>> {
        return response["list"] as List<Map<String, Any>>
    }

    fun mapToWeatherData(city: String, forecast: Map<String, Any>): WeatherData {
        val mainData = forecast["main"] as Map<String, Any>
        val weatherInfo = (forecast["weather"] as List<Map<String, Any>>).first()
        val forecastDateTime = forecast["dt_txt"] as String?

        return WeatherData(
            city = city,
            forecastDate = LocalDateTime.parse(forecastDateTime!!.replace(" ", "T")),
            description = weatherInfo["description"] as String,
            temperature = mainData["temp"] as Double,
            humidity = mainData["humidity"] as Int,
            iconCode = weatherInfo["icon"] as String,
            minTemperature = mainData["temp_min"] as Double,
            maxTemperature = mainData["temp_max"] as Double
        )
    }

    fun saveWeatherInDatabase(city: String, forecasts: List<Map<String, Any>>) {
        for (forecast in forecasts) {
            val weatherData = mapToWeatherData(city, forecast)
            weatherRepository.save(weatherData)
        }
    }

    fun fetchWeatherData(city: String): Map<String, Any> {
        val restTemplate = RestTemplate()
        val url = "$apiUrl?q=$city&appid=$apiKey&units=metric&lang=de"
        return restTemplate.getForObject(url)
    }


    fun getWeatherForecast(city: String, days: Int): List<ResponseWeatherData> {
        if (!weatherRepository.existsByCity(city) || DateTimeUtils.isOlderThanThreeHours(
                weatherRepository.findTopByCityOrderByForecastDateDesc(city).forecastDate
            )
        ) {
            weatherRepository.deleteByCity(city)
            saveWeatherForecasts(city)
            return calculateWeatherForecast(city, days)
        }
        return calculateWeatherForecast(city, days)
    }


    fun calculateWeatherForecast(city: String, days: Int): List<ResponseWeatherData> {

        var weatherList = weatherRepository.findAllByCity(city)

        val weatherListPerDay = weatherList.groupBy { it.forecastDate.toLocalDate() }.map { (date, entries) ->
            ResponseWeatherData(
                date = date,
                description = entries.map { it.description }.first().toString(), // TODO
                temperature = Math.round(entries.map { it.temperature }.average() * 100) / 100.0,
                minTemperature = entries.map { it.minTemperature }.min(),
                maxTemperature = entries.map { it.maxTemperature }.max(),
                humidity = entries.map { it.humidity }.average().toInt(),
                iconCode = entries.map { it.iconCode }.first().toString(),       //Todo
            )
        }


        return weatherListPerDay.take(days)
    }


    fun deleteAll() {
        weatherRepository.deleteAll()
    }


}

