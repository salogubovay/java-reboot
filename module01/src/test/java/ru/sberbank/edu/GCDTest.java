package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GCDTest {
    private GCD gcd = new GCD();
    
    /**
     * Проверка корректности НОД, когда первое число больше второго.
     */
    @Test
    public void should_returnCorrectGcd_whenFirstNumberIsGreaterThanSecond() {
        int firstNumber = 18;
        int secondNumber = 6;
        int expectedGcd = 6;
        int actualGcd = gcd.getDivisor(firstNumber, secondNumber);
        Assertions.assertEquals(expectedGcd, actualGcd);
    }

    /**
     * Проверка корректности НОД, когда первое число больше второго.
     */
    @Test
    public void should_returnCorrectGcd_whenSecondNumberIsGreaterThanFirst() {
        int firstNumber = 6;
        int secondNumber = 18;
        int expectedGcd = 6;
        int actualGcd = gcd.getDivisor(firstNumber, secondNumber);
        Assertions.assertEquals(expectedGcd, actualGcd);
    }
    
    /**
     * Проверка того, что пробрасывается исключение, если первый аргумент является отрицательным числом.
     */
    @Test
    public void should_throwException_whenFirstNumberIsNegative() {
        int firstNumber = -6;
        int secondNumber = 18;
        String expectedMessage = "Negative arguments are prohibited";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> gcd.getDivisor(firstNumber, secondNumber));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
    
    /**
     * Проверка того, что пробрасывается исключение, если второй аргумент является отрицательным числом.
     */
    @Test
    public void should_throwException_whenSecondNumberIsNegative() {
        int firstNumber = 6;
        int secondNumber = -18;
        String expectedMessage = "Negative arguments are prohibited";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> gcd.getDivisor(firstNumber, secondNumber));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
    
    /**
     * Проверка корректности НОД, когда первое число равно второму.
     */
    @Test
    public void should_returnCorrectGcd_whenTwoNumbersAreEqual() {
        int firstNumber = 1457;
        int secondNumber = firstNumber;
        int expectedGcd = firstNumber;
        int actualGcd = gcd.getDivisor(firstNumber, secondNumber);
        Assertions.assertEquals(expectedGcd, actualGcd);
    }
    
    /**
     * Проверка корректности НОД, когда один из аргументов равен 0.
     */
    @Test
    public void should_returnCorrectGcd_whenOneOfTheNumbersEqualsZero() {
        int firstNumber = 1457;
        int secondNumber = 0;
        int expectedGcd = firstNumber;
        int actualGcd = gcd.getDivisor(firstNumber, secondNumber);
        Assertions.assertEquals(expectedGcd, actualGcd);
    }
    
    /**
     * Проверка того, что возвращается 1, когда аргументы взаимнопростые числа.
     */
    @Test
    public void should_returnOne_whenNumbersAreCoprime() {
        int firstNumber = 23;
        int secondNumber = 24;
        int expectedGcd = 1;
        int actualGcd = gcd.getDivisor(firstNumber, secondNumber);
        Assertions.assertEquals(expectedGcd, actualGcd);
    }

}
