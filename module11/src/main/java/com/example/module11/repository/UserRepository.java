package com.example.module11.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.module11.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
