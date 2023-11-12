package ru.sberbank.edu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TravelServiceTest {
    private String cityName1 = "Dolgoprudny";
    private String cityLatitude1 = "55(56'00'')";
    private String cityLongitude1 = "37(30'00'')";
    private String cityName2 = "Khimki";
    private String cityLatitude2 = "55(53'21'')";
    private String cityLongitude2 = "37(26'42'')";
    private String cityName3 = "Mytishchi";
    private String cityLatitude3 = "55(55'00'')";
    private String cityLongitude3 = "37(46'00'')";    
    private String cityName4 = "Zelenograd";
    private String cityLatitude4 = "55(59'52'')";
    private String cityLongitude4 = "37(11'25'')";    
    private CityInfo city1 = new CityInfo(cityName1, new GeoPosition(cityLatitude1, cityLongitude1));
    private CityInfo city2 = new CityInfo(cityName2, new GeoPosition(cityLatitude2, cityLongitude2));
    private CityInfo city3 = new CityInfo(cityName3, new GeoPosition(cityLatitude3, cityLongitude3));
    private CityInfo city4 = new CityInfo(cityName4, new GeoPosition(cityLatitude4, cityLongitude4));
    
    /**
     * Проверка того, что метод add добавляет объекты CityInfo, если таких объектов ещё нет в списке cities
     */
    @Test
    void add_shouldAddCityInfoIfSuchCityDoesNotExistInTheList() {
        TravelService tsTest = new TravelService();
        tsTest.add(city1);
        tsTest.add(city2);
        Assertions.assertThat(tsTest.citiesNames().size()).isEqualTo(2);
    }
    
    /**
     * Проверка того, что метод add пробрасывает исключение, если объект CityInfo уже существует в списке cities
     */
    @Test
    void add_shouldThrowExceptionIfCityInfoExistsInTheList() {
        TravelService tsTest = new TravelService();
        tsTest.add(city1);
        assertThrows(IllegalArgumentException.class, () -> tsTest.add(city1));
    }
    
    /**
     * Проверка того, что метод remove пробрасывает исключение, если город с таким именем не существует в списке cities
     */
    @Test
    void remove_shouldThrowExceptionIfCityInfoDoesNotExistInTheList() {
        TravelService tsTest = new TravelService();
        tsTest.add(city1);
        assertThrows(IllegalArgumentException.class, () -> tsTest.remove(city2.getName()));
    }
    
    /**
     * Проверка того, что метод remove удаляет город из списка cities
     */
    @Test
    void remove_shouldCityFromTheList() {
        TravelService tsTest = new TravelService();
        tsTest.add(city1);
        Assertions.assertThat(tsTest.citiesNames().size()).isEqualTo(1);
        tsTest.remove(city1.getName());
        Assertions.assertThat(tsTest.citiesNames().size()).isEqualTo(0);
    }
    
    /**
     * Проверка того, что метод getDistance корректно рассчитывает расстояние между городами
     */
    @Test
    void getDistance_shouldCalculateCorrectDistanceBetweenCities() {
        TravelService tsTest = new TravelService();
        tsTest.add(city1);
        tsTest.add(city2);
        int distance = tsTest.getDistance(city1.getName(), city2.getName());
        int expectedDistance = 6;
        Assertions.assertThat(distance).isEqualTo(expectedDistance);
    }
    
    /**
     * Проверка того, что метод getCitiesNear возвращает список названий городов, расстояние до которых не превышает заданного
     */
    @Test
    void getCitiesNear_shouldReturnListWithWithNearestCities() {
        TravelService tsTest = new TravelService();
        int distance = 20;
        tsTest.add(city1);
        tsTest.add(city2);
        tsTest.add(city3);
        tsTest.add(city4);
        List<String> nearestCities = tsTest.getCitiesNear(city1.getName(), distance);
        Assertions.assertThat(nearestCities.size()).isEqualTo(2);
        Assertions.assertThat(nearestCities.contains(city2.getName())).isTrue();
        Assertions.assertThat(nearestCities.contains(city3.getName())).isTrue();
    }
}
