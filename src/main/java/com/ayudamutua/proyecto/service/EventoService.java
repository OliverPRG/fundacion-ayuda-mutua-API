package com.ayudamutua.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayudamutua.proyecto.model.Evento;
import com.ayudamutua.proyecto.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	public List<Evento> obtenerTodosLosEventos() {
		return eventoRepository.findAll();
	}

	public Evento crearEvento(Evento nuevoEvento) {
		return eventoRepository.save(nuevoEvento);
	}

	public Optional<Evento> buscarEventoPorId(Long id) {
		return eventoRepository.findById(id);
	}

	public void borrarEvento(Long id) {
		eventoRepository.deleteById(id);
	}
}