package ru.sberbank.edu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CityInfoTest {

    /**
     * Проверка того, что метод toString() возвращает корректное значение (типа String)
     */
    @Test
    void toString_shouldReturnCorrectString() {
        String latitudeInDegrees = "55(56'00'')";
        String longitudeInDegrees = "37(30'00'')";
        String cityName = "Dolgoprudny";
        String expectedString = "CityInfo [name=Dolgoprudny, position=GeoPosition [latitude=0.9762, longitude=0.6545]]";
        GeoPosition geoTest = new GeoPosition(latitudeInDegrees, longitudeInDegrees);
        CityInfo cityTest = new CityInfo(cityName, geoTest);
        Assertions.assertThat(cityTest.toString()).isEqualTo(expectedString);
    }

}
