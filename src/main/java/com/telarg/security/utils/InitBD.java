package com.telarg.security.utils;

import com.telarg.security.data.entities.Role;
import com.telarg.security.data.entities.User;
import com.telarg.security.repositories.RoleRepository;
import com.telarg.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitBD {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void init(){
        if (!userRepository.findByName("admin").isPresent()){
            User user = new User();
            user.setName("admin");
            user.setPassword("78[vVx-UUVS#&xX<");
            Role role = new Role();
            role.setName("ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            roleRepository.save(role);
            userRepository.save(user);
        }

    }

}