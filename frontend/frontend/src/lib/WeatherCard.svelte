<script>
    export let weather;
    export let icon;
</script>

<div class="weather-card">
    <h2>{new Date(weather.date).toLocaleDateString()}</h2>
    <p>Temperature: {weather.temperature}°C </p>
    <p>Min temperature: {weather.minTemperature}°C</p>
    <p>Max temperature: {weather.maxTemperature}°C</p>
    <p>Humidity: {weather.humidity}%</p>
    <p>Description: {weather.description}<img src={icon} alt="weather icon" class="weather-icon"></p>
</div>

<style>
    .weather-icon {
        width: 60px;
        height: 60px;
        vertical-align: middle;
        margin-left: 5px;
    }
</style>
