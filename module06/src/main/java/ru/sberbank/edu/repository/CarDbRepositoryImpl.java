package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    
    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement deleteByIdPreStmt;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.deleteByIdPreStmt = connection.prepareStatement(DELETE_CAR_BY_ID);
    }

    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    @Override
    public Boolean deleteById(String id) {
        try {
            deleteByIdPreStmt.setString(1, id);
            int rows = deleteByIdPreStmt.executeUpdate();
            return (rows == 0) ? false : true;
        } catch (SQLException e) {
            throw new RuntimeException (e.getMessage());
        }
    }

    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    /**
     * Метод создаёт (или обновляет) записи в соответствии с объектами в коллекции, передаваемой в качестве аргумента
     * @param Collection<Car> - коллекция объектов Car для загрузки в базу данных
     * @return Set<Car> - множество объектов Car, добавленных в базу данных.
     */
    @Override
    public Set<Car> createAll(Collection<Car> tCollection) {
        Set<Car> cars = new HashSet<>();
        for (Car car : tCollection) {
            try {
                cars.add(createOrUpdate(car));
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return cars;
    }

    
    /**
     * Метод возвращает множество объектов Car, которые хранятся в базе данных
     * @return - Set<Car> множество объектов Car, извлечённых из базы данных
     */
    @Override
    public Set<Car> findAll() {
        Set<Car> cars = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(1), resultSet.getString(2));
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return cars;
    }

    /**
     * Метод удаляет все записи, хранящиеся в базе данных
     * @return true - если все записи были удалены;
     *         false - если нет записей для удаления;
     */
    @Override
    public Boolean deleteAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM car")) {
           int rows = preparedStatement.executeUpdate();
           return (rows == 0) ? false : true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }        
    }
    
    
    /**
     * Метод ищет в базе данных записи, у которых в поле model проставлено значение из агрумента
     * @param model - модель автомобиля
     * @return Set<Car> - множество объектов Car модели model
     */
    @Override
    public Set<Car> findByModel(String model) {
        Set<Car> cars = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE model = ?")) {
            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(1), resultSet.getString(2));
                cars.add(car);
            }
            return cars;
         } catch (SQLException e) {
             throw new RuntimeException("While searching model = " + model + " error occured: " + e.getMessage());
         }  
    }
}
