package schwarz.it.lws.controller

import io.kotest.core.spec.style.FunSpec
import io.mockk.*
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import schwarz.it.lws.model.ResponseWeatherData
import schwarz.it.lws.service.WeatherService

class WeatherControllerTest : FunSpec({

    val weatherService = mockk<WeatherService>(relaxed = true)
    val mockMvc = MockMvcBuilders
        .standaloneSetup(WeatherController(weatherService))
        .build()


    test("deleteWeather") {

        every { weatherService.deleteAll() } just runs

        mockMvc.delete("/api/weather")
            .andExpect { status { isOk() } }

        verify { weatherService.deleteAll() }

    }

    test("getWeatherForecast") {

        val city = "Heilbronn"
        val days = 3

        every { weatherService.getWeatherForecast(city, days) } returns listOf(
            ResponseWeatherData(
                temperature = 10.0
            ),
            ResponseWeatherData(
                temperature = 10.0
            ),
            ResponseWeatherData(
                temperature = 10.0
            )
        )

        mockMvc.get("/api/weather/$city?days=$days")
            .andExpect {
                status { isOk() }
                jsonPath("$.size()") {value(3)}
            }


    }

})
