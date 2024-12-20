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

    let {weatherData = []} = $props();

    async function searchWeather() {
        const response = await fetch(`http://localhost:8080/api/weather/${city}`);
        if (!response.ok) {
            alert('City not found or API error');
            return;
        }
        weatherData = await response.json();
    }
</script>


<main>
    <h1>Weather Forecast</h1>

    <div class="search">
        <input bind:value={city} placeholder="Enter city"/>
        <button on:click={searchWeather}>Search</button>
    </div>

    {#if weatherData}
        <div class="weather-container" class:hidden={!weatherData || weatherData.length === 0}>
            <div class="forecast">
                {#each weatherData as day, index}
                    <WeatherCard weather={day} icon={getImage(day)}/>
                {/each}
            </div>
        </div>

    {/if}
</main>

<style>
    :global(body) {
        background-image: url('background.jpeg');
        font-family: 'Arial',sans-serif;
        background-size: cover;
        background-attachment: fixed;
    }

    main {
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
    }

    .search {
        display: flex;
        margin-bottom: 20px;
    }

    .hidden {
        display: none;
    }

    input {
        flex-grow: 1;
        padding: 12px 20px;
        font-size: 16px;
        border: 2px solid var(--primary-color);
        border-radius: 25px 0 0 25px;
        outline: none;
        transition: all 0.3s ease;
    }

    button {
        padding: 10px 20px;
    }

    .weather-container {
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 20px;
        background-color: #c6e9ff;
        border-radius: 16px;
        padding: 20px;
        box-shadow: 0 4px 6px dimgray;
    }


</style>