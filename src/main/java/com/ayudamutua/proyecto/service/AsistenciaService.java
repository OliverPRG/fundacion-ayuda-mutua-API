package com.ayudamutua.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ayudamutua.proyecto.model.Asistencia;
import com.ayudamutua.proyecto.model.Evento;
import com.ayudamutua.proyecto.model.Usuario;
import com.ayudamutua.proyecto.repository.AsistenciaRepository;
import com.ayudamutua.proyecto.repository.EventoRepository;
import com.ayudamutua.proyecto.repository.UsuarioRepository;

@Service
public class AsistenciaService {

	@Autowired
	private AsistenciaRepository asistenciaRepository;
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Asistencia registrarAsistencia(Long usuarioId, Long eventoId) {

		boolean yaExiste = asistenciaRepository.existsByUsuarioIdAndEventoId(usuarioId, eventoId);

		if (yaExiste) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya está registrado en este evento");
		}

		Usuario usuarioEncontrado = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		Evento eventoEncontrado = eventoRepository.findById(eventoId)
				.orElseThrow(() -> new RuntimeException("Evento no encontrado"));

		Asistencia nuevaAsistencia = new Asistencia();

		nuevaAsistencia.setUsuario(usuarioEncontrado);
		nuevaAsistencia.setEvento(eventoEncontrado);

		return asistenciaRepository.save(nuevaAsistencia);

	}

	public List<Asistencia> obtenerAsistenciasPorEvento(Long eventoId) {

		return asistenciaRepository.findByEventoId(eventoId);

	}
}