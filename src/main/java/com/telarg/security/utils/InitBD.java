package com.telarg.security.utils;

import com.telarg.security.data.entities.*;
import com.telarg.security.repositories.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClasificacionesRepository clasificacionesRepository;

    @Autowired
    private ReporteRepository reporteRepository;

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
            User user2 = new User();
            user2.setName("admin2");
            user2.setPassword("78[vVx-UUVS#&xX<");
            user2.setRoles(roles);
            userRepository.save(user2);

            log.info("ñlakjsd entreee");
            for (Classifications classification: Classifications.values()){
                log.info("añlksdjfñlaskjdfñalksdjf " + classification);
                Clasificacion clasificacion = new Clasificacion(classification);
                clasificacionesRepository.save( clasificacion );
                reporteRepository.save(new Reporte(clasificacion));
            }
            log.info("ñlakjsd salu");
            setHistoric();
        }
    }

    private void setHistoric(){
        try {
            File file = new File("/home/ecs-ms/historico.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                log.info(st);
                String[] historico = st.split("\\|");
                historicoRepository.save(new Historico(
                    new Clasificacion(Classifications.fromValue(historico[0])),
                    historico[1]
                ));
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

}