package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GreetingImplTest {

    /**
     * Проверка того, что метод возращает корректное значение
     */
    @Test
    public void shoul_returnCorrectValue() {
        String expectedValue = "Football";
        String actualValue = new GreetingImpl().getBestHobby();
        Assertions.assertEquals(expectedValue, actualValue);
    }

}
