package com.telarg.security.controllers;

import com.telarg.security.data.entities.MensajesDesconocidos;
import com.telarg.security.data.entities.Reporte;
import com.telarg.security.data.vo.ClassifyRequest;
import com.telarg.security.data.vo.ClassifyResponse;
import com.telarg.security.repositories.HistoricoRepository;
import com.telarg.security.repositories.MensajesDesconocidosRepository;
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

    @Autowired
    private MensajesDesconocidosRepository mensajesDesconocidosRepository;

    @PostMapping("/classify")
    public ResponseEntity<Object> classify(@Valid @RequestBody ClassifyRequest classifyRequest){
        ClassifyResponse classifyResponse= classifyClient.getClasification(classifyRequest);
        Classifications classifications = Classifications.fromValue(classifyResponse.getTag());
        if ( classifications != null && !classifications.equals(Classifications.DESCONOCIDO)) {
            Optional<Reporte> reporteResponse = reporteRepository.findById(classifications);
            if (reporteResponse.isPresent()) {
                Reporte reporte;
                reporte = reporteResponse.get();
                reporte.setContador(reporte.getContador() + 1);
                reporteRepository.save(reporte);
            }
        } else {
            if (!mensajesDesconocidosRepository.findByMessage(classifyRequest.getMessage().trim()).isPresent())
                mensajesDesconocidosRepository.save(new MensajesDesconocidos(classifyRequest.getMessage().trim()));
        }
        return new ResponseEntity<>(classifyResponse, HttpStatus.CREATED);
    }

}