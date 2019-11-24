package com.telarg.security.utils;

import com.telarg.security.data.entities.Historico;
import com.telarg.security.data.entities.Role;
import com.telarg.security.data.entities.User;
import com.telarg.security.repositories.HistoricoRepository;
import com.telarg.security.repositories.RoleRepository;
import com.telarg.security.repositories.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitBD {

    private Log log = LogFactory.getLog(InitBD.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

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
        setHistoric();
    }

    private void setHistoric(){
        try {
            File file = new ClassPathResource("data/historico.txt").getFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                log.info(st);
                String[] historico = st.split("\\|");
                historicoRepository.save(new Historico(
                    Classifications.fromValue(historico[1]),
                    historico[0],
                    historico[1]
                ));
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

}