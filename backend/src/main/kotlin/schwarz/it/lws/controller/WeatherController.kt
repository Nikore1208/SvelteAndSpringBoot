package schwarz.it.lws.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import schwarz.it.lws.model.ResponseWeatherData
import schwarz.it.lws.model.WeatherData
import schwarz.it.lws.service.WeatherService

@RestController
@RequestMapping("/api/weather")
class WeatherController(private val weatherService: WeatherService) {


  /*  @GetMapping("/{city}")
    fun getWeatherForecast(@PathVariable city: String, @RequestParam days: Int): ResponseEntity<List<WeatherData>> {
        require(days in 1..5) { "Anzahl der Tage muss zwischen 1 und 5 liegen" }
        return ResponseEntity.ok(weatherService.getWeatherForecast(city, days))
    }*/

    @GetMapping("/{city}")
    fun getWeatherForecast(@PathVariable city: String): ResponseEntity<List<ResponseWeatherData>> {
        return ResponseEntity.ok(weatherService.getWeatherForecast(city))
    }


}
