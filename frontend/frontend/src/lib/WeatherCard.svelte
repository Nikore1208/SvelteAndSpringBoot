<script>
    export let weather;
</script>

<style>
    .weather-card {
        border: 1px solid #ddd;
        padding: 20px;
        border-radius: 5px;
        margin-bottom: 20px;
    }
</style>

<div class="weather-card">
    <h2>{new Date(weather.dt * 1000).toLocaleDateString()}</h2>
    <p>Temperature: {weather.main.temp}°C</p>
    <p>Min temperature: {weather.main.temp_min}°C</p>
    <p>Max temperature: {weather.main.temp_max}°C</p>
    <p>Humidity: {weather.main.humidity}%</p>
    <p>Air Pressure: {weather.main.pressure} hPa</p>
    <p>Description: {weather.weather[0].description}</p>
</div>

