<script>
  import WeatherCard from './lib/WeatherCard.svelte';

  let city = '';
  let weatherData = null;
  let selectedDay = 0;

  async function searchWeather() {
    const response = await fetch(`https://api.openweathermap.org/data/2.5/forecast?q=${city}&units=metric&appid=9eb6056c21bd7f742bff107b5794c961`);
    if (!response.ok) {
      alert('City not found or API error');
      return;
    }
    const data = await response.json();
    weatherData = data.list.filter((_, index) => index % 8 === 0);
    console.log(weatherData)
  }

  function selectDay(index) {
    selectedDay = index;
  }
</script>

<style>
  main {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
  }
  .search {
    display: flex;
    margin-bottom: 20px;
  }
  input {
    flex-grow: 1;
    padding: 10px;
  }
  button {
    padding: 10px 20px;
  }
  .weather-container {
    display: flex;
    justify-content: space-between;
  }
  .forecast {
    display: flex;
    flex-direction: column;
  }
  .forecast button {
    margin-bottom: 10px;
  }
  .active {
    background-color: #007bff;
    color: white;
  }
</style>

<main>
  <h1>Weather Forecast</h1>

  <div class="search">
    <input bind:value={city} placeholder="Enter city" />
    <button on:click={searchWeather}>Search</button>
  </div>


  {#if weatherData}
    <div class="weather-container">
      <WeatherCard weather={weatherData[selectedDay]} />

      <div class="forecast">
        {#each weatherData as day, index}
          <button class:active={index === selectedDay} on:click={() => selectDay(index)}>
            {new Date(day.dt * 1000).toLocaleDateString()}
          </button>
        {/each}
      </div>
    </div>
  {/if}
  <div>
    <img src="./assets/clear-day.svg" alt="clear-day">
  </div>
</main>
