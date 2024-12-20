<script>
    export let weather;
    export let icon;
</script>

<div class="weather-card">
    <div class="weather-header">
        <h2>{new Date(weather.date).toLocaleDateString()}</h2>
        <img src={icon} alt="weather icon" class="weather-icon"/>
    </div>
    <div class="weather-details">
        <p><strong>Temperature:</strong> {weather.temperature}°C</p>
        <p><strong>Min:</strong> {weather.minTemperature}°C</p>
        <p><strong>Max:</strong> {weather.maxTemperature}°C</p>
        <p><strong>Humidity:</strong> {weather.humidity}%</p>
        <p><strong>Description:</strong> {weather.description}</p>
    </div>
</div>

<style>
    .weather-card {
        background: linear-gradient(135deg, #a2c2e4, #d3e5f7);
        color: #333;
        border-radius: 12px;
        padding: 20px;
        max-width: 320px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        font-family: 'Arial', sans-serif;
        margin: 15px;
        border: 1px solid #c0d9f7;
    }

    .weather-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 10px 20px rgba(0, 0, 0, 1);
    }

    .weather-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: 2px solid #b0c9e7;
        padding-bottom: 10px;
        margin-bottom: 15px;
    }

    h2 {
        margin: 0;
        font-size: 1.4em;
        color: #5f8ca3;
    }

    .weather-icon {
        width: 50px;
        height: 50px;
    }

    .weather-details p {
        margin: 8px 0;
        line-height: 1.5;
        font-size: 1.1em;
    }

    .weather-details strong {
        font-weight: bold;
        color: #5f8ca3;
    }

    .weather-details p {
        color: #333;
    }


</style>
