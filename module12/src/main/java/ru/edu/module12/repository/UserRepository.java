package ru.edu.module12.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.edu.module12.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
