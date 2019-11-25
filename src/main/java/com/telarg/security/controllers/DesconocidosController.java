package com.telarg.security.controllers;

import com.telarg.security.repositories.MensajesDesconocidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesconocidosController {

    @Autowired
    private MensajesDesconocidosRepository mensajesDesconocidosRepository;

    @GetMapping("/desconocidos")
    public ResponseEntity<Object> getDesconocidos(){
        return new ResponseEntity<>(mensajesDesconocidosRepository.findAll(), HttpStatus.OK);
    }

}
