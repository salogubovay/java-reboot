package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomDigitComparatorTest {

    private List<Integer> testList;
    
    @BeforeEach
    public void initList() {
        testList = new ArrayList<>();
    }

    /**
     * Проверка того, что положительные чётные элементы после сортировки будут находиться перед нечётными отрицательными
     */
    @Test
    public void sortingUsingComparator_shouldPlacePositiveEvenValuesBeforeNegativeOddValues() {
        Integer[] testArray = new Integer[] {-5, 8};
        Integer[] expectedArray = new Integer[] {8, -5};
        Collections.addAll(testList, testArray);    
        testList.sort(new CustomDigitComparator());
        Assertions.assertThat(testList.toArray()).isEqualTo(expectedArray);
    }
    
    /**
     * Проверка того, что чётные элементы после соритровки расположены по возрастанию
     */
    
    @Test
    public void sortingUsingComparator_shouldPlaceEvenValuesInIncreasingOrder() {
        Integer[] testArray = new Integer[] {-8, 32, 6, -10,};
        Integer[] expectedArray = new Integer[] {-10, -8, 6, 32};
        Collections.addAll(testList, testArray);    
        testList.sort(new CustomDigitComparator());
        Assertions.assertThat(testList.toArray()).isEqualTo(expectedArray);
    }
    
    /**
     * Проверка того, что отрицательные чётные элементы после сортировки будут находиться перед отрицательными нечётными
     */
    @Test
    public void sortingUsingComparator_shouldPlaceNegativeEvenValuesBeforeNegativeOddValues() {
        Integer[] testArray = new Integer[] {-8, -3, -5, -100};
        Integer[] expectedArray = new Integer[] {-100, -8, -5, -3};
        Collections.addAll(testList, testArray);    
        testList.sort(new CustomDigitComparator());
        Assertions.assertThat(testList.toArray()).isEqualTo(expectedArray);
    }

}
