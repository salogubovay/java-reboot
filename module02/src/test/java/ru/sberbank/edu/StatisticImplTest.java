package ru.sberbank.edu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StatisticImplTest {

    /**
     * Проверка того, что метод generateStatisticReport корректно формирует отчёт по статистике файла
     */
    @Test
    public void generateStatisticReport_shouldCreateCorrectString() {
        int lines = 100;
        int spaces = 150;
        String longestLine = "the longest line";
        StatisticImpl testStatistic = new StatisticImpl(lines, spaces, longestLine);
        String expectedReport = "lines = 100\nspaces = 150\nlongest line = the longest line\n";;
        Assertions.assertThat(testStatistic.generateStatisticReport()).isEqualTo(expectedReport);
    }

}
