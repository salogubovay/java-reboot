package ru.sberbank.edu;

// интерфейс можно менять
public interface Statistic {
    /**
     * @return количество строк в файле
     */
    int getLineCount();
    
    /**
     * @return количество пробелов в файле
     */
    int getSpaceCount();
    
    /**
     * @return самая длинная строка в файле
     */
    String getLongestLine();
    
    /**
     * Метод сохраняет статистику в хранилище
     * @param storage - хранилище, в которое нужно сохранить файл
     */
    void save(Storage storage);
    
    /**
     * @return Метод возвращает итоговую статистику как объект типа String
     */
    String generateStatisticReport();
}
