package ru.sberbank.edu;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GeoPositionTest {
    
    /**
     * Проверка того, что конструктор корректно преобразует градусы в радианы
     */
    @Test
    void constructor_shouldConvertDegreesRadiansCorrectly() {
        String latitudeInDegrees = "55";
        String longitudeInDegrees = "37";
        double latitude = 0.9599;
        double longitude = 0.6458;
        double epsilon = 0.0001;
        GeoPosition geoTest = new GeoPosition(latitudeInDegrees, longitudeInDegrees);
        Assertions.assertThat(Math.abs(latitude - geoTest.getLatitude()) < epsilon).isTrue();
        Assertions.assertThat(Math.abs(longitude - geoTest.getLongitude()) < epsilon).isTrue();

    }
    
    /**
     * Проверка того, что конструктор корректно преобразует градусы в радианы, если указаны минуты и секунды
     */
    @Test
    void constructor_shouldConvertDegreesWithMinutesAndSecondsToRadiansCorrectly() {
        String latitudeInDegrees = "55(56'00'')";
        String longitudeInDegrees = "37(30'00'')";
        double latitude = 0.9762;
        double longitude = 0.6545;
        double epsilon = 0.0001;
        GeoPosition geoTest = new GeoPosition(latitudeInDegrees, longitudeInDegrees);
        Assertions.assertThat(Math.abs(latitude - geoTest.getLatitude()) < epsilon).isTrue();
        Assertions.assertThat(Math.abs(longitude - geoTest.getLongitude()) < epsilon).isTrue();
    }
       
    /**
     * Проверка того, что метод toString() возвращает корректное значение (типа String)
     */
    @Test
    void toString_shouldReturnCorrectString() {
        String latitudeInDegrees = "55(56'00'')";
        String longitudeInDegrees = "37(30'00'')";
        String expected = "GeoPosition [latitude=0.9762, longitude=0.6545]";
        GeoPosition geoTest = new GeoPosition(latitudeInDegrees, longitudeInDegrees);
        Assertions.assertThat(geoTest.toString()).isEqualTo(expected);
    }
    
    /**
     * Проверка того, что конструктор должен пробрасывать исключение, когда формат аргументов некорректен
     */
    @Test
    void constructor_shouldThrowIllegalArgumentExceptionWhenArgumentsFormatIsIncorrect() {
        String latitudeInDegrees = "55 56 00";
        String longitudeInDegrees = "37 30";
        assertThrows(IllegalArgumentException.class, () -> new GeoPosition(latitudeInDegrees, longitudeInDegrees));
    }

}
