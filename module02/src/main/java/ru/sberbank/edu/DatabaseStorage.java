package ru.sberbank.edu;

/**
 * Пример класса для сохранения данных в БД
 * @author Aleksandr Salogubov
 *
 */
public class DatabaseStorage implements Storage {

    @Override
    public void save(String data) {
        System.out.println("Saving data to db...");    
    }
}
