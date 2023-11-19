package ru.sberbank.edu;

import java.util.Objects;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor. parse and set latitude and longitude in radian
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        if (!isCorrectFormat(latitudeGradus) || !isCorrectFormat(longitudeGradus)) {
            throw new IllegalArgumentException("Incorrect format: latitudeGradus = " + latitudeGradus + "or longitudeGradus = " + longitudeGradus);
        }
        latitude = convertDegreesToRadians(latitudeGradus);
        longitude = convertDegreesToRadians(longitudeGradus);
    }
    
    /**
     * Проверка формата строки
     * @param gradus
     * @return true - если строка соответствует шаблону;
     *         false - если строка не соответствует шаблону;
     */
    private boolean isCorrectFormat(String gradus) {
        String pattern = "^\\d+([(]\\d{2}'\\d{2}''[)])?";
        return gradus.matches(pattern);
    }
    
    public double convertDegreesToRadians(String gradus) {
        double radians, degrees, minutes, seconds;
        radians = degrees = minutes = seconds = 0;
        
        if (gradus.contains("(")) {
            int minutesIndex = gradus.indexOf("(") + 1;
            int secondsIndex = gradus.indexOf("'") + 1;
            degrees = Integer.valueOf(gradus.substring(0, minutesIndex - 1));
            minutes = Integer.valueOf(gradus.substring(minutesIndex, secondsIndex - 1));
            seconds = Integer.valueOf(gradus.substring(secondsIndex, secondsIndex + 2));
        } else {
            degrees = Integer.valueOf(gradus);
        }
        radians = (degrees + (minutes + seconds / 60) / 60) * Math.PI / 180;
        return radians;
    }
  
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
    @Override
    public String toString() {
        return "GeoPosition [latitude=" + String.format("%.4f", latitude) + ", longitude=" + String.format("%.4f", longitude) + "]";
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GeoPosition other = (GeoPosition) obj;
        return Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
                && Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
    }
}