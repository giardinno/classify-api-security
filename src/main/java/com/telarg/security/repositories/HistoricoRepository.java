package com.telarg.security.repositories;

import com.telarg.security.data.entities.Historico;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HistoricoRepository extends CrudRepository<Historico, Integer > {

    public Optional<Historico> findByMessage(String message);

}