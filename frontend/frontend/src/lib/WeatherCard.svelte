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
<!--    <h2>{new Date(weather.dt * 1000).toLocaleDateString()}</h2>-->
<!--    <h3>{new Date(weather.dt*1000).toLocaleTimeString()}</h3>-->
    <p>Temperature: {weather.temperature}°C</p>
    <p>Min temperature: {weather.minTemperature}°C</p>
    <p>Max temperature: {weather.maxTemperature}°C</p>
    <p>Humidity: {weather.humidity}%</p>
    <p>Description: {weather.description}</p>
</div>

