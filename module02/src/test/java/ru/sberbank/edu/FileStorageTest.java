package ru.sberbank.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FileStorageTest {

    /**
     * Проверка того, что метод save корректно сохраняет содержимое объекта String в текстовый файл
     */
    @Test
    public void save_shouldSaveDataToFile() {
        String testData = "test line\n";
        FileStorage fileStorage = new FileStorage();
        fileStorage.save(testData);
        File reportFile = new File("report.txt");
        Assertions.assertThat(readFile(reportFile)).isEqualTo(testData);
        reportFile.delete();
    }
    
    /**
     * @param testFile - имя тестового файла
     * @return метод возвращает содержимое файла в качестве объекта String
     */
    private String readFile(File testFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            StringBuilder fileData = new StringBuilder();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                fileData.append(currentLine + '\n');
            }
            return fileData.toString();
        } catch (IOException e) {
            throw new RuntimeException ("Cannot read file. Message = " + e.getMessage());
        }
    }
}
