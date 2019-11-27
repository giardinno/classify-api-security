package com.telarg.security.controllers;

import com.telarg.security.repositories.ReporteRepository;
import com.telarg.security.utils.Classifications;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportsController {

    @Autowired
    private ReporteRepository reporteRepository;

    @GetMapping("/reports/{classification-id}")
    public ResponseEntity<Object> getReport(@PathVariable("classification-id") String classificationId){

        try {
            return new ResponseEntity<>(
                    reporteRepository.findById(Classifications.values()[Integer.parseInt(classificationId)]).get(),
                    HttpStatus.OK
            );
        }catch (Exception e){
            JSONObject response = new JSONObject();
            response.put("message", "Clasificación no valida");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}