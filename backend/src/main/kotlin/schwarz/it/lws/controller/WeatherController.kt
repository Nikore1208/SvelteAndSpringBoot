package schwarz.it.lws.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import schwarz.it.lws.model.ResponseWeatherData
import schwarz.it.lws.service.WeatherService

@CrossOrigin(origins = ["http://localhost:8080"])
@RestController
@RequestMapping("/api/weather")
class WeatherController(private val weatherService: WeatherService) {



    @GetMapping("/{city}")
    fun getWeatherForecast(@PathVariable city: String, @RequestParam(defaultValue = "3") days: Int): ResponseEntity<List<ResponseWeatherData>> {
        require(days in 1..5) { "Anzahl der Tage muss zwischen 1 und 5 liegen" }
        return ResponseEntity.ok(weatherService.getWeatherForecast(city, days))
    }


    @GetMapping("/DELETE/ALL")
    fun deleteWeather(){
        weatherService.deleteAll()
    }


}
