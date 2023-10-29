package ru.sberbank.edu;

import java.io.File;

/**
 * Класс для запуска приложения по анализу файла.
 * @author Aleksandr Salogubov
 */

public class App {
    /**
     * 
     * @param args - первый параметр должен содержать имя файла дла анализа
     */
    public static void main( String[] args ) {
        if (args.length == 0) {
            System.out.println("File name is missing");
        }
        File file = new File(args[0]);
        StatisticService statService = new StatisticServiceImpl();
        statService.analyseFile(file, new FileStorage());
    }
}
