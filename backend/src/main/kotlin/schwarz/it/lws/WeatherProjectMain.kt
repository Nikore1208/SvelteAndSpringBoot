package schwarz.it.lws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import schwarz.it.lws.service.WeatherService

@SpringBootApplication
class WeatherProjectMain

fun main(args: Array<String>) {
	runApplication<WeatherProjectMain>(*args)
}
