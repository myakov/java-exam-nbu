package com.exam.java.repository;

import com.exam.java.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
