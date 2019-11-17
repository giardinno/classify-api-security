package com.telarg.security.controllers;

import com.telarg.security.data.entities.Historico;
import com.telarg.security.data.entities.Reporte;
import com.telarg.security.data.vo.ClassifyRequest;
import com.telarg.security.data.vo.ClassifyResponse;
import com.telarg.security.repositories.HistoricoRepository;
import com.telarg.security.repositories.ReporteRepository;
import com.telarg.security.services.fiengClients.ClassifyClient;
import com.telarg.security.utils.Classifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ClassifyController {

    @Autowired
    private ClassifyClient classifyClient;

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @PostMapping("/classify")
    public ResponseEntity<Object> classify(@Valid @RequestBody ClassifyRequest classifyRequest){
        ClassifyResponse classifyResponse= classifyClient.getClasification(classifyRequest);
        Classifications classifications = Classifications.fromValue(classifyResponse.getTag());
        if ( classifications != null && !classifications.equals(Classifications.DESCONOCIDO)) {
            Optional<Reporte> reporteResponse = reporteRepository.findById(classifications);
            Reporte reporte;
            if (reporteResponse.isPresent()) {
                reporte = reporteResponse.get();
                reporte.setContador(reporte.getContador() + 1);
            } else
                reporte = new Reporte(classifications, 1);
            reporteRepository.save(reporte);
        } else {
            if (!historicoRepository.findByMessage(classifyRequest.getMessage().trim()).isPresent())
                historicoRepository.save(new Historico(Classifications.DESCONOCIDO, classifyRequest.getMessage().trim(), Classifications.DESCONOCIDO.value()));
        }
        return new ResponseEntity<>(classifyResponse, HttpStatus.CREATED);
    }

}