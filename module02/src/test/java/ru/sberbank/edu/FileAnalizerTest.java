package ru.sberbank.edu;

import java.io.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FileAnalizerTest {
    
    /**
     * Проверка того, что метод countSpaces возвращает корректное количество пробелов в строке
     */
    @Test
    public void countSpaces_shouldReturnCorrectNumberOfSpaces() {
        int expectedSpaceCount = 11;
        String testLine = "h e l l o  w o r l d !";
        Assertions.assertThat(new FileAnalizer().countSpaces(testLine)).isEqualTo(expectedSpaceCount);
    }
    
    /**
     * Проверка того, что метод getStatistic корректно определяет количество строк в файле, количество пробелов и самое длинную строку
     */
    @Test
    public void getStatistic_shouldReturnStatisticObjectWithCorrectInformation() {
        File resourcesDirectory = new File ("src/test/resources");
        String testFileName = "test.txt";
        File testFile = new File(resourcesDirectory.getAbsolutePath() + "/" + testFileName);
        String expectedStatistics = "lines = 3\nspaces = 15\nlongest line = h e l l o , w o r l d !\n";
        Statistic statistic = new FileAnalizer().getStatistic(testFile);
        Assertions.assertThat(statistic.generateStatisticReport()).isEqualTo(expectedStatistics);
    }

}
