package com.ayudamutua.proyecto.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> manejarErroresDeValidacion(MethodArgumentNotValidException ex) {
		
		Map<String, String> errores = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String nombreCampo = ((FieldError) error).getField();
			String mensaje = error.getDefaultMessage();
			errores.put(nombreCampo, mensaje);
		});
		return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> manejarReglaDeNegocio (IllegalArgumentException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
