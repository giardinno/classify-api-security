package com.telarg.security.controllers;

import com.telarg.security.data.entities.Clasificacion;
import com.telarg.security.data.entities.Historico;
import com.telarg.security.data.entities.MensajesDesconocidos;
import com.telarg.security.data.vo.DesconocidosResponse;
import com.telarg.security.repositories.HistoricoRepository;
import com.telarg.security.repositories.MensajesDesconocidosRepository;
import com.telarg.security.utils.Classifications;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DesconocidosController {

    @Autowired
    private MensajesDesconocidosRepository mensajesDesconocidosRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping("/desconocidos")
    public ResponseEntity<Object> getDesconocidos(){
        return new ResponseEntity<>(new DesconocidosResponse(mensajesDesconocidosRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/desconocidos/{id}/{classId}")
    public ResponseEntity<Object> saveDesconocido(@PathVariable("id") int id, @PathVariable("classId") int classId){
        Optional<MensajesDesconocidos> mensajesDesconocidos = mensajesDesconocidosRepository.findById(id);
        if (mensajesDesconocidos == null || !mensajesDesconocidos.isPresent()){
            JSONObject response = new JSONObject();
            response.put("message", "Id de mensaje no valido");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.BAD_REQUEST
            );
        }
        Classifications classifications = null;
        try {
            classifications = Classifications.values()[classId];
        }catch(Exception e){
            JSONObject response = new JSONObject();
            response.put("message", "Id de Clasificación no valido");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.BAD_REQUEST
            );
        }
        historicoRepository.save(
            new Historico(
                new Clasificacion(classifications),
                mensajesDesconocidos.getMessage()
            )
        );
        mensajesDesconocidosRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
