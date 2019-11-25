package com.telarg.security.controllers;

import com.telarg.security.data.entities.Clasificacion;
import com.telarg.security.data.entities.Historico;
import com.telarg.security.data.entities.MensajesDesconocidos;
import com.telarg.security.repositories.HistoricoRepository;
import com.telarg.security.repositories.MensajesDesconocidosRepository;
import com.telarg.security.utils.Classifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesconocidosController {

    @Autowired
    private MensajesDesconocidosRepository mensajesDesconocidosRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping("/desconocidos")
    public ResponseEntity<Object> getDesconocidos(){
        return new ResponseEntity<>(mensajesDesconocidosRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/desconocidos/{id}")
    public ResponseEntity<Object> saveDesconocido(@PathVariable("id") int id){
        MensajesDesconocidos  mensajesDesconocidos = mensajesDesconocidosRepository.findById(id).get();
        historicoRepository.save(new Historico(new Clasificacion(Classifications.RECONOCIMIENTO), mensajesDesconocidos.getMessage()));
        mensajesDesconocidosRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
