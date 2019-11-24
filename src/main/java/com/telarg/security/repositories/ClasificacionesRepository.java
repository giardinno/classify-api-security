package com.telarg.security.repositories;

import com.telarg.security.data.entities.Clasificaciones;
import com.telarg.security.data.entities.Historico;
import com.telarg.security.data.entities.MensajesDesconocidos;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClasificacionesRepository extends CrudRepository<Clasificaciones, Integer > {

}