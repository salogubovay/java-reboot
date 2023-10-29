package ru.sberbank.edu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс {@code FileStorage} содержит метод для сохранения данных в текстовый файл
 * @author Aleksandr Salogubov
 *
 */
public class FileStorage implements Storage {
    @Override
    public void save(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create report. Message = " + e.getMessage());
        }
    }
}
