package com.ayudamutua.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayudamutua.proyecto.model.Evento;
import com.ayudamutua.proyecto.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@GetMapping
	public List<Evento> obtenerTodos() {
		return eventoService.obtenerTodosLosEventos();
	}

	@PostMapping
	public Evento crearEvento(@RequestBody Evento nuevoEvento) {
		return eventoService.crearEvento(nuevoEvento);
	}
}