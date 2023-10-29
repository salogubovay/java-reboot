package ru.sberbank.edu;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomArrayImplTest {
    
    /**
     * Проверка того, что метод isEmpty возвращает true, если CustomArrayImpl не содержит элементов
     */
    @Test
    public void isEmpty_shouldReturnTrueWhenCustomArrayImplHasNoElements() {
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        Assertions.assertThat(testArray.isEmpty()).isTrue();
    }
    
    /**
     * Проверка того, что метод getCapacity возвращает корректную текущую вместимость CustomArrayImpl
     */
    @Test
    public void getCapacity_shouldReturnCorrectLength() {
        int initialCapacity = 7;
        CustomArrayImpl<Integer> testArray = new CustomArrayImpl<>(7);
        Assertions.assertThat(testArray.getCapacity()).isEqualTo(initialCapacity);
    }
    
    /**
     * Проверка того, что конструктор пробрасывает исключение, если аргумент является отрицательным числом
     */
    @Test
    public void constructor_shouldThrowExceptionWhenInitialCapacityIsNegative() {
        int initialCapacity = -7;
        assertThrows(IllegalArgumentException.class, () -> {
            CustomArrayImpl<Integer> testArray = new CustomArrayImpl<>(initialCapacity);
            }
        );
    }
    
    /**
     * Проверка того, что метод add добавляет элемент в CustomArrayImpl
     */
    @Test
    public void add_shouldAddElementInCustomArrayImpl() {
        int expectedSize = 1;
        CustomArrayImpl<Integer> testArray = new CustomArrayImpl<>();
        int integer1 = 10;
        testArray.add(integer1);
        Assertions.assertThat(testArray.size()).isEqualTo(expectedSize);
    }

    /**
     * Проверка того, что метод get возвращает корректный элемент
     */
    @Test
    public void get_shouldReturnSameElementFromCustomArrayImplThatHasBeenAdded() {
        String s1 = "test string 1";
        int sIndex = 0;
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.add(s1);
        Assertions.assertThat(testArray.get(sIndex)).isEqualTo(s1);
    }
    
    /**
     * Проверка того, что метод get пробрасывает исключение, когда index не входит в границы массива значений
     */
    @Test
    public void get_shouldThrowExceptionWhenIndexIsOutOfBounds() {
        String s1 = "test string 1";
        int sIndex = 100;
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.add(s1);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> testArray.get(sIndex));
    }
    
    /**
     * Проверка того, что метод addAll пробрасывает исключение, когда аргмент = null
     */
    @Test
    public void addAll_shouldThrowExceptionWhenArgumentIsNull() {
        String[] strings = null;
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        assertThrows(IllegalArgumentException.class, () -> testArray.addAll(strings));
    }
    
    /**
     * Проверка того, что метод addAll добавляет все элементы из массива аргумента
     */
    @Test
    public void addAll_shouldAddAllElementsFromArgumentArray() {
        String[] strings = {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        for (int i = 0; i < strings.length; ++i) {
            Assertions.assertThat(testArray.get(i)).isEqualTo(strings[i]);
        }
    }
    
    /**
     * Проверка того, что метод set заменяет новым элемент по указанному индексу
     */
    @Test
    public void set_shouldReplaceElementOnGivenIndex() {
        String[] strings = {"one", "two", "three"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        String replacement = "replace";
        int replaceIdx = 1;
        testArray.set(replaceIdx, replacement);
        Assertions.assertThat(testArray.get(replaceIdx)).isEqualTo(replacement);
    }
    
    /**
     * Проверка того, что метод remove удаляет элемент и уменьшает индексы оставшихся на 1
     */
    @Test
    public void remove_shouldRemoveElementsAndShiftIndicesOfOtherElements() {
        String[] strings = {"one", "two", "three"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        int removeIdx = 1;
        testArray.remove(removeIdx);
        Assertions.assertThat(testArray.get(0)).isEqualTo(strings[0]);
        Assertions.assertThat(testArray.get(1)).isEqualTo(strings[2]);
    }
    
    /**
     * Проверка того, что метод indexOf возвращает корректный индекс элемента
     */
    @Test
    public void indexOf_shouldReturnCorrectIndexOfElement() {
        String[] strings = {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        int idx = 3;
        Assertions.assertThat(testArray.indexOf(strings[idx])).isEqualTo(idx);
    }
    
    /**
     * Проверка того, что метод toArray возвращает массив со всеми элементами CustomArrayImpl
     */
    @Test
    public void toArray_shouldReturnArrayWithAllElements() {
        String[] strings = {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        Assertions.assertThat(testArray.toArray()).isEqualTo(strings);
    }


    /**
     * Проверка того, что метод reverse разворачивает порядок элементов в массиве CustomArrayImpl
     */
    @Test
    public void reverse_shouldReverseElementsOrderInCustomArrayImpl() {
        String[] strings = {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        testArray.reverse();
        for (int i = 0; i < strings.length; ++i) {
            Assertions.assertThat(testArray.get(strings.length - 1 - i)).isEqualTo(strings[i]);
        }
    }

    /**
     * Проверка того, что метод addAll добавляет все элементы из коллекции аргумента
     */
    @Test
    public void addAll_shouldAddAllElementsFromArgumentCollection() {
        List<String> strings = new ArrayList<>(Arrays.asList("one", "two", "three", "four", "five", "six"));
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        for (int i = 0; i < strings.size(); ++i) {
            Assertions.assertThat(testArray.get(i)).isEqualTo(strings.get(i));
        }
    }
    
    /**
     * Проверка того, что метод addAll добавляет все элементы из массива начиная с указанного индекса и смещает текущие элементы
     */
    @Test
    public void addAll_shouldAddAllElementsFromArgumentArrayFromArgumentIndexAndShiftCurrentElements() {
        String[] strings = new String[] {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        String[] addStrings = new String[] {"add_one", "add_two", "add_three"};
        int addIndex = 0;
        testArray.addAll(addIndex, addStrings);
        for (int i = 0; i < addIndex; ++i) {
            Assertions.assertThat(testArray.get(i)).isEqualTo(strings[i]);
        }
        for (int i = addIndex; i < addIndex + addStrings.length; ++i) {
            Assertions.assertThat(testArray.get(i)).isEqualTo(addStrings[i - addIndex]);
        }
        for (int i = addIndex + addStrings.length; i < strings.length + addStrings.length; ++i) {
            Assertions.assertThat(testArray.get(i)).isEqualTo(strings[i - addStrings.length]);
        }
    }
    
    /**
     * Проверка того, что метод toString возвращает корректную строку с элементами массива
     */
    @Test
    public void toString_shouldReturnCorrectStringWithArrayValues() {
        String[] strings = new String[] {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        String expectedString = "[ one two three four five six ]";
        Assertions.assertThat(testArray.toString()).isEqualTo(expectedString);
    }
    
    /**
     * Проверка того, что метод remove корректно удаляет элемент из массива и смещает остальные элементы
     */
    @Test
    public void remove_shouldRemoveElementFromArrayAndShiftOtherElements() {
        String[] strings = new String[] {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        String deleteString = "two";
        testArray.remove(deleteString);
        String expectedString = "[ one three four five six ]";
        Assertions.assertThat(testArray.toString()).isEqualTo(expectedString);
    }
    
    /**
     * Проверка того, что метод contains возвращает true, когда элемент существует в массиве
     */
    @Test
    public void contains_shouldReturnTrueWhenElementExists() {
        String[] strings = new String[] {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        int checkIndex = 2;
        Assertions.assertThat(testArray.contains(strings[checkIndex])).isTrue();
    }
    
    /**
     * Проверка того, что метод contains возвращает false, когда элемент отсутствует в массиве
     */
    @Test
    public void contains_shouldReturnFalseWhenElementDoesNotExist() {
        String[] strings = new String[] {"one", "two", "three", "four", "five", "six"};
        CustomArrayImpl<String> testArray = new CustomArrayImpl<>();
        testArray.addAll(strings);
        String nonExistingString = "non existing string";
        Assertions.assertThat(testArray.contains(nonExistingString)).isFalse();
    }
}
