package ru.sberbank.edu;

/**
 * Тестовый пример использования WeatherCache
 *
 */
public class App 
{
    public static void main( String[] args ) {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        WeatherInfo weatherInfo = weatherCache.getWeatherInfo("Dolgoprudny");
        System.out.println(weatherInfo);
        weatherCache.getWeatherInfo("Dolgoprudny");
        weatherCache.getWeatherInfo("Khimki");
        weatherCache.getWeatherInfo("qwerty");
    }
}
