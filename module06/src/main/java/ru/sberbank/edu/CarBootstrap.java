package ru.sberbank.edu;


import org.h2.tools.Server;
import ru.sberbank.edu.dbconnection.H2DbEmbedded;
import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarDbRepositoryImpl;
import ru.sberbank.edu.repository.CarRepository;
import ru.sberbank.edu.service.CarService;
import ru.sberbank.edu.service.CarServiceImpl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

public class CarBootstrap {
    public static void main(String[] args) throws Exception {
        Server server = Server.createTcpServer(args).start();
        H2DbEmbedded.initDb();

        try(H2DbEmbedded h2DbEmbedded = new H2DbEmbedded()) {
            CarRepository carRepository = new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
            CarService carService = new CarServiceImpl(carRepository);

            carService.addCar("777", "Lada");

            // Test check start
            String readAllCarsSql = "SELECT * FROM car";
            Statement statement = H2DbEmbedded.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(readAllCarsSql);

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String model = resultSet.getString(2);
                System.out.println("id=" + id + "; model=" + model);
            }

            carService.addCar("888", "Lada");
            carService.addCar("666", "UAZ");
            
            System.out.println("\nFind all after addition: ");
            Set<Car> cars = carRepository.findAll();
            for (Car car : cars) {
                System.out.println(car);
            }
            
            System.out.println("\nFind by model = UAZ");
            Set<Car> carsModel = carRepository.findByModel("UAZ");
            for (Car car : carsModel) {
                System.out.println(car);
            }

            carService.deleteCar("777");
            System.out.println("\nFind all after deletion of id = 777: ");
            Set<Car> carsAfterIdDeletion = carRepository.findAll();
            for (Car car : carsAfterIdDeletion) {
                System.out.println(car);
            }
            
            carRepository.deleteAll();
            System.out.println("\nFind all after deletion of all records: ");
            Set<Car> carsAfterDeletion = carRepository.findAll();
            for (Car car : carsAfterDeletion) {
                System.out.println(car);
            }
            // Test end            
        }
        server.stop();
    }
}
