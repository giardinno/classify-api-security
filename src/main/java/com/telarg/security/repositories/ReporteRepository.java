package com.telarg.security.repositories;

import com.telarg.security.data.entities.Reporte;
import com.telarg.security.utils.Classifications;
import org.springframework.data.repository.CrudRepository;

public interface ReporteRepository extends CrudRepository<Reporte, Classifications> {
}