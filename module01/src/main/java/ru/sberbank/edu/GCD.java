package ru.sberbank.edu;

/**
 * Класс {@code GCD} включает в себя метод, который возвращает наибольший общий делитель двух целых положительных чисел.
 * @author  Aleksandr Salogubov
 */

public class GCD implements CommonDivisor {
    
    /**
     * @param firstNumber - первое положительное целое число
     * @param secondNumber - второе положительное целое число
     * @return наибольший общий делитель
     */
    public int getDivisor(int firstNumber, int secondNumber) {
        if (firstNumber < 0 || secondNumber < 0) {
            throw new IllegalArgumentException("Negative arguments are prohibited");
        }
        
        while (secondNumber > 0) {
            int temp = secondNumber;
            secondNumber = firstNumber % secondNumber;
            firstNumber = temp;
        }
        return firstNumber;
    }
}
