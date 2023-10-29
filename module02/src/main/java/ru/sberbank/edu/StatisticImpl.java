package ru.sberbank.edu;

/**
 * Класс {@code StatisticImpl} хранит информацию о статистике файла и содержит методы для предоставления и сохранения информации о ней 
 * @author  Aleksandr Salogubov
 *
 */
public class StatisticImpl implements Statistic {
    private int lineCount;
    private int spaceCount;
    private String longestLine;
    
    /**
     * Конструктор для создания объекта со статистикой файла
     * @param lineCount - количество строк в файле
     * @param spaceCount - количество пробелов в файле
     * @param longestLine - самая длинная строка в файле
     */
    StatisticImpl(int lineCount, int spaceCount, String longestLine) {
        this.lineCount = lineCount;
        this.spaceCount = spaceCount;
        this.longestLine = longestLine;
    }
    
    @Override
    public String getLongestLine() {
        return longestLine;
    }

    @Override
    public int getLineCount() {
        return lineCount;
    }

    @Override
    public int getSpaceCount() {
        return spaceCount;
    }
    
    @Override
    public void save(Storage storage) {
        storage.save(generateStatisticReport());
    }
    
    @Override
    public String generateStatisticReport() {
        StringBuilder data = new StringBuilder();
        data.append("lines = " + lineCount + '\n');
        data.append("spaces = " + spaceCount + '\n');
        data.append("longest line = " + longestLine + '\n');
        return data.toString();
    }
    
}
