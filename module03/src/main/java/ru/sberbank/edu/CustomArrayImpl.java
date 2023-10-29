package ru.sberbank.edu;

import java.util.Collection;
import java.util.*;

/**
 * Класс {@code CustomArrayImpl} реализует интерфейс CustomArray
 * 
 * @author Aleksandr Salogubov
 */

public class CustomArrayImpl<T> implements CustomArray<T>{
    
    /**
     * Размер CustomArrayImpl - сколько элементов содержит
     */
    private int size;
    
    /**
     * Вместимость CustomArrayImpl по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 3;
    
    /**
     * Максимальная вместимость CustomArrayImpl
     */
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    
    /**
     * Массив, который содержит элементы, добавляемые в CustomArrayImpl
     */
    private Object[] elements;
    
    /**
     * Конструктор без параметров - создаёт массив длиной DEFAULT_CAPACITY
     */
    CustomArrayImpl() {
        elements = new Object[DEFAULT_CAPACITY];;
    }
    
    CustomArrayImpl(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
        }
        elements = new Object[initialCapacity];
    }
    
    /**
     * @return Метод возвращает количество элементов в CustomArrayImpl
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * @return Метод возвращает true, если CustomArrayImpl не содержит элементов, иначе false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Метод увеличивает массив elements в 2 раза
     */
    private Object[] grow(int minCapacity) {
        int newLength = Math.max(minCapacity, elements.length * 2);
        return  elements = Arrays.copyOf(elements, Math.min(MAX_CAPACITY, newLength));
    }

    @Override
    public boolean add(T item) {
        ensureCapacity(1);
        elements[size] = item;
        ++size;
        return true;
    }

    @Override
    public boolean addAll(T[] items) {
        if (items == null) {
            throw new IllegalArgumentException("Null arguement");
        }     
        ensureCapacity(items.length);
        System.arraycopy(items, 0, elements, size, items.length);
        size += items.length;
        return true;
    }

    @Override
    public boolean addAll(Collection<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Null argument");
        }
        ensureCapacity(items.size());
        for (T item : items) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, T[] items) {
        if (items == null) {
            throw new IllegalArgumentException("Null argument");
        }
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index: " + index);        
        }
        ensureCapacity(items.length);
        System.arraycopy(elements, index, elements, index + items.length, size - index);
        System.arraycopy(items, 0, elements, index, items.length);
        size += items.length;
        return false;
    }
    
    /**
     * Метод проверяет, что индекс находится в границах массива с элементами
     * @param index - проверяемый индекс
     * @return Метод возвращает true, если массив по указанному индексу содержит элемент.
     * @throws ArrayIndexOutOfBoundsException - если индекс выходит за границы массива c элементами

     */
    private boolean checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index: " + index);
        }
        return true;
    }
    
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        elements[index] = item;
        return oldValue;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        --size;
    }

    @Override
    public boolean remove(T item) {
        int index = indexOf(item);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    @Override
    public boolean contains(T item) {
        int index = indexOf(item);
        return index == -1 ? false : true;
    }

    @Override
    public int indexOf(T item) {
        for (int i = 0; i < size; ++i) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (size + newElementsCount > elements.length) {
            grow(size + newElementsCount);
        }
    }
    
    @Override
    public int getCapacity() {
        return elements.length;
    }

    @Override
    public void reverse() {
        Object[] revElements = new Object[size];
        for (int i = 0; i < size; ++i) {
            revElements[size - 1 - i] = elements[i];
        }
        elements = revElements;
    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("[ ");
        for (int i = 0; i < size; i++) {
            strBuilder.append(elements[i].toString() + " ");
        }
        strBuilder.append(']');
        return strBuilder.toString();
    }
    
    @Override
    public Object[] toArray() {
        Object[] output = new Object[size];
        System.arraycopy(elements, 0, output, 0, size);
        return output;
    }

}
