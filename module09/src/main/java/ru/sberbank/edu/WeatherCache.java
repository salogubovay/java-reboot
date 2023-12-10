package ru.sberbank.edu;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Weather cache.
 */
public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private final Object lock = new Object();
    private WeatherProvider weatherProvider;

    /**
     * Default constructor.
     */
    public WeatherCache() {
    }

    @Autowired
    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }
    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    public WeatherInfo getWeatherInfo(String city) {
        synchronized(lock) {
            WeatherInfo weatherInfo = cache.get(city);
            if ((weatherInfo == null) || weatherInfo.getExpiryTime().isBefore(LocalDateTime.now())) {
                weatherInfo = weatherProvider.get(city);
                if (weatherInfo != null) {
                    weatherInfo.setExpiryTime(LocalDateTime.now().plusMinutes(5));
                    cache.put(city, weatherInfo);
                }
            }
            return weatherInfo;
        }
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {
        synchronized (lock) {
            cache.remove(city);
        }
    }
}