package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if (cityInfo == null) {
            throw new IllegalArgumentException("CityInfo = null");
        }
        
        if (cityExists(cityInfo.getName())) {
            throw new IllegalArgumentException("City = " + cityInfo.getName() + " already exists.");
        }
        
        cities.add(cityInfo);
    }
    
    /**
     * Проверка того, что город уже существует в списке cities
     * @param cityInfo
     * @return true - если город с таким именем уже добавлен в список;
     *         false - если города с таким именем нет в списке
     */
    public boolean cityExists(String cityName) {
        return cities.stream()
                .filter(ci -> ci.getName().equals(cityName))
                .count() == 1;
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        CityInfo cityInfo = getCityInfo(cityName);
        cities.remove(cityInfo);
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream().map(ci -> ci.getName()).collect(Collectors.toList());
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        double distance, cosLat1, cosLat2, sinLat1, sinLat2, deltaLong, cosDeltaLong, sinDeltaLong, y, x, ad, earthRadius;
        earthRadius = 6372795;              
        CityInfo srcCity = getCityInfo(srcCityName);
        CityInfo destCity = getCityInfo(destCityName);
        cosLat1 = Math.cos(srcCity.getPosition().getLatitude());
        cosLat2 = Math.cos(destCity.getPosition().getLatitude());
        sinLat1 = Math.sin(srcCity.getPosition().getLatitude());
        sinLat2 = Math.sin(destCity.getPosition().getLatitude());        
        deltaLong = destCity.getPosition().getLongitude() -  srcCity.getPosition().getLongitude();
        cosDeltaLong = Math.cos(deltaLong);
        sinDeltaLong = Math.sin(deltaLong);
        y = Math.sqrt(Math.pow(cosLat2 * sinDeltaLong, 2) + Math.pow(cosLat1 * sinLat2 - cosLat2 * sinLat1 * cosDeltaLong, 2));
        x = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDeltaLong;
        ad = Math.atan2(y, x);
        distance = ad * earthRadius / 1000;
        return (int) Math.round(distance);
    }
    
    public CityInfo getCityInfo(String cityName) {
        if (cityName == null) {
            throw new IllegalArgumentException("cityName = null");
        }
        if (!cityExists(cityName)) {
            throw new IllegalArgumentException("City = " + cityName + " does not exist.");
        }
        return  cities.stream().filter(ci -> ci.getName().equals(cityName)).findAny().orElse(null);
    }
    
    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {     
        if (!cityExists(cityName)) {
            throw new IllegalArgumentException("City = " + cityName + " does not exist.");
        }
        return cities.stream()
                     .filter(ci -> (getDistance(cityName, ci.getName()) <= radius) && !ci.getName().equals(cityName))
                     .map(ci -> ci.getName())
                     .collect(Collectors.toList());
    }
}
