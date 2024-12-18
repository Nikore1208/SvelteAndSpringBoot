package schwarz.it.lws.service

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import schwarz.it.lws.repository.WeatherRepository

class WeatherServiceTest : FunSpec({



 val weatherRepository = mockk<WeatherRepository>(relaxed = true)
 val weatherService = WeatherService(weatherRepository, apiUrl = "", apiKey = "")

    test("deleteAll") {

     every { weatherRepository.deleteAll() } just Runs

     weatherService.deleteAll()

     verify { weatherRepository.deleteAll() }

    }


    test("getWeatherForecast"){



    }

})
