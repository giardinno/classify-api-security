package com.telarg.security.services.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest wr){
		
		ExceptionResponseDTO exception = new ExceptionResponseDTO();
		exception.setFecha(new Date());
		exception.setMensaje("Vaya!, esto si que es raro, ocurrió un error dificil de explicar");
		exception.setHttpCode("500");
		exception.setHttpError( HttpStatus.INTERNAL_SERVER_ERROR.name() );
		
		return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
}
