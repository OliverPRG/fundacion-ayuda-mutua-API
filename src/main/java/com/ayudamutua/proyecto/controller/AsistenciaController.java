package com.ayudamutua.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayudamutua.proyecto.model.Asistencia;
import com.ayudamutua.proyecto.service.AsistenciaService;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {

	@Autowired
	private AsistenciaService asistenciaService;

	@PostMapping
	public Asistencia registrar(@RequestParam Long usuarioId, @RequestParam Long eventoId) {

		return asistenciaService.registrarAsistencia(usuarioId, eventoId);
	}

	@GetMapping("/evento/{eventoId}")
	public List<Asistencia> obtenerPorEvento(@PathVariable Long eventoId) {

		return asistenciaService.obtenerAsistenciasPorEvento(eventoId);
	}
}