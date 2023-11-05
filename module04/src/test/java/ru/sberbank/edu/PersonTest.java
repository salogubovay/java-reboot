package ru.sberbank.edu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonTest {

    /**
     * Проверка того, что метод equals при сравнении объектов не учитывает регистр (в полях типа String).
     */
    @Test
    public void equals_shouldIgnoreCase() {
        Person first = new Person("ivan", "Tver", 34);
        Person second = new Person("Ivan", "tver", 34);
        assertTrue(first.equals(second));
    }
    
    /**
     * Проверка того, что метод hashCode возвращает одинаковое значение для объектов, для которых метод equals возвращает значение true
     */
    @Test
    public void hashCode_shouldReturnSameValueForEqualObjects() {
        Person first = new Person("ivan", "Tver", 34);
        Person second = new Person("Ivan", "tver", 34);
        assertTrue(first.equals(second));
        Assertions.assertThat(first.hashCode()).isEqualTo(second.hashCode());
    }
    
    /**
     * Проверка того, что метод equals возвращает false, если поля сравниваемых объектов отличаются
     */
    @Test
    public void equals_shouldReturnFalseIfFieldsInTwoObjectsAreNotEqual() {
        Person first = new Person("ivon", "Tver", 34);
        Person second = new Person("Ivan", "tver", 34);
        assertFalse(first.equals(second));
    }
    
    /**
     * Проверка того, что метод hashCode возвращает разные значения для объектов, у которых отличаются поля
     */
    @Test
    public void hashCode_shouldReturnDifferentValuesForObjectsWithNotEqualFields() {
        Person first = new Person("ivon", "Tver", 34);
        Person second = new Person("Ivan", "tver", 34);
        assertFalse(first.equals(second));
        Assertions.assertThat(first.hashCode()).isNotEqualTo(second.hashCode());
    }
    
    /**
     * Проверка того, что метод compareTo сначала сравнивает значения поля city
     */
    @Test
    public void compareTo_shouldCompareAtFirstPlaceCityThanName() {
        Person first = new Person("Ivan", "Pskov", 34);
        Person second = new Person("Andrey", "Tver", 34);
        List<Person> testList = new ArrayList<>();
        testList.add(second);
        testList.add(first);
        Collections.sort(testList);
        Assertions.assertThat(testList.get(0)).isEqualTo(first);
        Assertions.assertThat(testList.get(1)).isEqualTo(second);
    }
    
    /**
     * Проверка того, что метод compareTo сначала сравнивает значения поля city
     */
    @Test
    public void compareTo_shouldCompareAtSecondPlaceNameValues() {
        Person first = new Person("Ivan", "Tver", 34);
        Person second = new Person("Andrey", "Tver", 34);
        List<Person> testList = new ArrayList<>();
        testList.add(first);
        testList.add(second);
        Collections.sort(testList);
        Assertions.assertThat(testList.get(0)).isEqualTo(second);
        Assertions.assertThat(testList.get(1)).isEqualTo(first);
    }
    
    /**
     * Проверка того, что метод compareTo не сравнивает возраст
     */
    @Test
    public void compareTo_shouldNotCompareAge() {
        Person first = new Person("Ivan", "Tver", 34);
        Person second = new Person("Ivan", "Tver", 35);
        List<Person> testList = new ArrayList<>();
        testList.add(second);
        testList.add(first);
        Collections.sort(testList);
        Assertions.assertThat(testList.get(0)).isEqualTo(second);
        Assertions.assertThat(testList.get(1)).isEqualTo(first);
    }
    
    /**
     * Проверка того, что метод toString возвращает значение в корректном формате
     */
    @Test
    public void toString_shouldReturnValueInCorrectFormat() {
        Person person = new Person("Ivan", "Tver", 34);
        String expectedString = "name = Ivan; city = Tver; age = 34;";
        Assertions.assertThat(person.toString()).isEqualTo(expectedString);
    }

}
