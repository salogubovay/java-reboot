package ru.sberbank.edu;

import java.io.File;

/**
 * Класс {@code StatisticServiceImpl}, который реализует метод для сбора статистики о файле и сохранение статистики в текстовый файл
 * @author Aleksandr Salogubov
 *
 */
public class StatisticServiceImpl implements StatisticService {

    @Override
    public Statistic analyseFile(File file, Storage storage) {
        Statistic statistic = new FileAnalizer().getStatistic(file);
        statistic.save(storage);
        return statistic;
    }
    
}
