package schwarz.it.lws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherProjectMain

fun main(args: Array<String>) {
	runApplication<WeatherProjectMain>(*args)
}
