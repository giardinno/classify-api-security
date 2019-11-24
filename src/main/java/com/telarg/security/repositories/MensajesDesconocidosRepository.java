package com.telarg.security.repositories;

import com.telarg.security.data.entities.MensajesDesconocidos;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MensajesDesconocidosRepository extends CrudRepository<MensajesDesconocidos, Integer > {

    public Optional<MensajesDesconocidos> findByMessage(String message);

}