<script>
    let city = 'Heilbronn';
    let temperature = 22;
    let description = 'Sonnig';
    let icon = '☀️';
</script>

<style>
    main {
        text-align: center;
        padding: 1em;
        max-width: 240px;
        margin: auto;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
</style>

<main>
    <h1>Wetter in {city}</h1>
    <p>Temperatur: {temperature} °C</p>
    <p>Beschreibung: {description} {icon}</p>
</main>


