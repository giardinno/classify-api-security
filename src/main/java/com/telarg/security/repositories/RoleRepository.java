package com.telarg.security.repositories;

import com.telarg.security.data.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}