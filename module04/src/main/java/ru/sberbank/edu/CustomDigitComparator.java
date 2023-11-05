package ru.sberbank.edu;

import java.util.Comparator;

/**
 * Класс {@code CustomDigitComparator} реализует интерфейс Comparator<Integer>
 * 
 * @author Aleksandr Salogubov
 */
public class CustomDigitComparator implements Comparator<Integer> {

    /**
     * Метод определяет следующее сравнение: чётные числа меньше нечётных.
     * @param o1 - объект Integer для сравнения
     * @param o2 - объект Integer для сравнения
     * @return -1 - если первый объект по порядку идёт раньше второго; 
     *          1 - если первый объект по порядку идёт позже второго;
     *          0 - если объекты равны
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 % 2 != 0 && o2 % 2 == 0) {
            return 1;
        } else if (o1 % 2 == 0 && o2 % 2 != 0) {
            return -1;
        } else {
            return o1 - o2;
        }
    }

}
