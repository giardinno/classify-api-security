package com.telarg.security.repositories;

import com.telarg.security.data.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByName(String name);

}