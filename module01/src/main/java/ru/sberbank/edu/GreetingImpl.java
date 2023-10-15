package ru.sberbank.edu;

/**
 * Класс {@code GreetingImpl} включает в себя метод, который позволяет получить значение поля bestHobby
 * @author  Aleksandr Salogubov
 */


public class GreetingImpl implements Greeting {
    
    /**
     * Поле, в котором хранится любимое хобби
     */
    private String bestHobby = "Football";

    /**
     * Метод возвращает значение поля bestHobby
     */        
    public String getBestHobby() {
        return bestHobby;
    }
}

