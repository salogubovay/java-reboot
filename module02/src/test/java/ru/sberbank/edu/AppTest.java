package ru.sberbank.edu;

import java.io.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    
    /**
     * Метод проверяет, что при запуске метод main создаётся файл report.txt
     */
    @Test
    public void app_shouldGetCorrectStatistics() {
        String[] args = new String[] { "src/test/resources/hello_world.txt" };
        App.main(args);
        File result = new File("report.txt");
        Assertions.assertThat(result.exists()).isTrue();
        result.delete();
    }

}
