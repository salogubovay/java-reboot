package ru.sberbank.edu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.json.*;

public class WeatherProvider {

    private RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "71a3c06566c6bd42a8cd5aa6cbb20643";
    private String resourceUrl = "http://api.openweathermap.org/data/2.5/weather";

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        String url = buildUrl(city);
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String weatherJson = response.getBody();
            return parseJson(weatherJson);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Метод извлекает информацию из json строки и заполняет поля объекта WeatherInfo
     * @param json - строка для парсинга
     * @return - объект WeatherInfo
     */
    private WeatherInfo parseJson(String json) {
        WeatherInfo weatherInfo = new WeatherInfo();
        JSONObject obj = new JSONObject(json);
        weatherInfo.setCity(obj.getString("name"));
        weatherInfo.setDescription(obj.getJSONArray("weather").getJSONObject(0).getString("description"));
        weatherInfo.setShortDescription(obj.getJSONArray("weather").getJSONObject(0).getString("main"));
        weatherInfo.setTemperature(obj.getJSONObject("main").getDouble("temp"));
        weatherInfo.setFeelsLikeTemperature(obj.getJSONObject("main").getDouble("feels_like"));
        weatherInfo.setPressure(obj.getJSONObject("main").getDouble("pressure"));    
        weatherInfo.setWindSpeed(obj.getJSONObject("wind").getDouble("speed"));
        return weatherInfo;
    }
    
    /**
     * Метод формирует url строку
     * @param city - название города
     * @return - url строка
     */
    private String buildUrl(String city) {
        StringBuilder sb = new StringBuilder();
        sb.append(resourceUrl)
            .append("?q=" + city)
            .append("&appid=" + apiKey);
        return sb.toString();
    }
}
