package ru.edu.module13.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.edu.module13.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
