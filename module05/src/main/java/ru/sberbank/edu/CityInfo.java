package ru.sberbank.edu;

import java.util.Objects;

/**
 * City info
 */
public class CityInfo {

    private String name;
    private GeoPosition position;

    /**
     * Ctor.
     *
     * @param name     - city name
     * @param position - position
     */
    public CityInfo(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }
    @Override
    public String toString() {
        return "CityInfo [name=" + name + ", position=" + position + "]";
    }

    public String getName() {
        return name;
    }

    public GeoPosition getPosition() {
        return position;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CityInfo other = (CityInfo) obj;
        return Objects.equals(name, other.name) && Objects.equals(position, other.position);
    }
}