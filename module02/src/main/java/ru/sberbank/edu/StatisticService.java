package ru.sberbank.edu;

import java.io.File;

/**
 * Интерфейс, который содержит метод для сбора статистики о файле
 * @author Aleksandr Salogubov
 *
 */
public interface StatisticService {
    /**
     * 
     * @param file - файл для анализа
     * @param storage - хранилище, в которе необходимо сохранить статистику
     * @return информацию о статистике файла в виде объекта Statistic
     */
    public Statistic analyseFile(File file, Storage storage);
}
