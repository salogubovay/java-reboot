package ru.sberbank.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс {@code FileAnalizer} содержит метод, который собирает статистику о файле и возвращает объект Statistic
 * @author Aleksandr Salogubov
 *
 */
public class FileAnalizer {
    /**
     * 
     * @param file - файл для анализа
     * @return статистику файла - объект Statistic
     */
    public Statistic getStatistic(File file) {
        int lines = 0;
        int spaces = 0;
        String longestLine = "";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                ++lines;
                spaces += countSpaces(currentLine);
                if (longestLine.length() < currentLine.length()) {
                    longestLine = currentLine;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file. Message = " + e.getMessage());
        }
        
        return new StatisticImpl(lines, spaces, longestLine);   
    }
    
    /**
     * @param line - строка
     * @return количество пробелов в строке
     */
    public int countSpaces(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == ' ' ) {
                ++count;
            }
        }
        return count;
    }
}
