package com.ayudamutua.proyecto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayudamutua.proyecto.dto.AsistenciaRequestDTO;
import com.ayudamutua.proyecto.dto.AsistenciaResponseDTO;
import com.ayudamutua.proyecto.service.AsistenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {

	private final AsistenciaService asistenciaService;

	public AsistenciaController(AsistenciaService asistenciaService) {
		this.asistenciaService = asistenciaService;
	}

	@PostMapping
	public ResponseEntity<AsistenciaResponseDTO> registrar(@Valid @RequestBody AsistenciaRequestDTO requestDTO) {

		AsistenciaResponseDTO response = asistenciaService.registrarAsistencia(requestDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/evento/{eventoId}")
	public ResponseEntity<List<AsistenciaResponseDTO>> obtenerPorEvento(@PathVariable Long eventoId) {
		List<AsistenciaResponseDTO> asistencias = asistenciaService.obtenerAsistenciasPorEvento(eventoId);
		return ResponseEntity.ok(asistencias);
	}
}