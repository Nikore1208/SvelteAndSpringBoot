<script>
    import WeatherCard from './lib/WeatherCard.svelte';
    import ClearDay from './assets/clear-day.svg';
    import Cloudy from './assets/cloudy.svg';
    import Rain from './assets/rain.svg';
    import Snow from './assets/snow.svg';

    function getImage(weather) {
        const code = weather.iconCode;
        const firstTwoChars = code.slice(0, 2);

        if (firstTwoChars === '01') return ClearDay;
        if (['02', '03', '04'].includes(firstTwoChars)) return Cloudy;
        if (['09', '10', '11'].includes(firstTwoChars)) return Rain;
        if (firstTwoChars === '13') return Snow;

        return Cloudy;
    }

    let city = '';
    let isLoading = false;
    let errorMessage = '';
    let weatherData = [];

    async function searchWeather() {
        if (!city) return;
        isLoading = true;
        errorMessage = '';
        const response = await fetch(`http://localhost:8080/api/weather/${city}?days=5`);

        if (!response.ok) {
            errorMessage = 'City not found or API error';
            isLoading = false;
            return;
        }

        weatherData = await response.json();
        isLoading = false;
    }
</script>

<main>
    <h1>Weather Forecast</h1>

    <div class="search">
        <input bind:value={city} placeholder="Enter city"/>
        <button on:click={searchWeather} disabled={isLoading}>Search</button>
    </div>

    {#if isLoading}
        <div class="loading">Loading...</div>
    {/if}

    {#if errorMessage}
        <div class="error">{errorMessage}</div>
    {/if}

    {#if weatherData.length > 0}
        <div class="weather-container">
            <div class="forecast">
                {#each weatherData as day}
                    <WeatherCard weather={day} icon={getImage(day)} />
                {/each}
            </div>
        </div>
    {/if}
</main>

<style>
    :global(body) {
        background-image: url('src/assets/cloudyBackground.jpeg');
        font-family: 'Arial', sans-serif;
        background-size: cover;
        background-attachment: fixed;
        color: #fff;
        margin: 0;
        padding: 0;
        transition: background-color 0.5s ease, color 0.5s ease;
    }

    main {
        max-width: 900px;
        margin: 0 auto;
        padding: 40px;
        text-align: center;
        background-color: rgba(0, 0, 0, 0.6);
        border-radius: 20px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        margin-top: 20px;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    main:hover {
        transform: translateY(-5px);
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
    }

    h1 {
        font-size: 2.5rem;
        color: #fff;
        margin-bottom: 30px;
        font-weight: 700;
        text-transform: uppercase;
    }

    .search {
        display: flex;
        justify-content: center;
        margin-bottom: 30px;
        gap: 15px;
    }

    .search input {
        flex-grow: 1;
        padding: 14px 20px;
        font-size: 16px;
        border: 2px solid #fff;
        border-radius: 30px 0 0 30px;
        outline: none;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
    }

    .search input:focus {
        border-color: #49a6e9;
        box-shadow: 0 0 12px rgba(73, 80, 87, 0.5);
    }

    .search button {
        padding: 14px 22px;
        background-color: #49a6e9;
        color: white;
        font-size: 16px;
        border: none;
        border-radius: 30px;
        cursor: pointer;
        transition: background-color 0.3s ease, transform 0.3s ease;
    }

    .search button:hover {
        background-color: #007bff;
        transform: scale(1.05);
    }

    .loading {
        font-size: 1.5rem;
        color: #fff;
        animation: fadeIn 1s ease-in-out infinite;
    }

    .error {
        color: red;
        font-size: 1.2rem;
        font-weight: bold;
    }

    .weather-container {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(270px, 1fr));
        gap: 20px;
        background-color: rgba(0, 0, 0, 0.4);
        border-radius: 20px;
        padding: 30px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        margin-top: 40px;
        transition: all 0.4s ease;
    }

    .weather-card {
        background-color: #fff;
        border-radius: 12px;
        padding: 20px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        text-align: center;
        transition: transform 0.3s ease, box-shadow 0.3s ease, background-color 0.3s ease;
    }

    .weather-card:hover {
        transform: scale(1.05);
        box-shadow: 0 8px 15px rgba(0, 0, 0, 0.2);
        background-color: #f8f9fa;
    }

    .weather-card h3 {
        font-size: 1.5rem;
        margin-bottom: 10px;
        color: #333;
    }

    .weather-card p {
        font-size: 1rem;
        color: #555;
        margin: 5px 0;
    }

    .weather-icon {
        font-size: 4rem;
        margin-bottom: 10px;
        color: #49a6e9;
    }

    @keyframes fadeIn {
        0% {
            opacity: 0;
        }
        100% {
            opacity: 1;
        }
    }

    /* Responsive Anpassungen */
    @media (max-width: 768px) {
        main {
            padding: 20px;
        }

        .search {
            flex-direction: column;
        }

        .search input, .search button {
            width: 100%;
            margin-bottom: 10px;
        }

        .weather-container {
            padding: 20px;
        }
    }
</style>