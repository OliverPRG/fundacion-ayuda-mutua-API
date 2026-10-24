package com.ayudamutua.proyecto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ayudamutua.proyecto.dto.AsistenciaRequestDTO;
import com.ayudamutua.proyecto.dto.AsistenciaResponseDTO;
import com.ayudamutua.proyecto.model.Asistencia;
import com.ayudamutua.proyecto.model.Evento;
import com.ayudamutua.proyecto.model.Usuario;
import com.ayudamutua.proyecto.repository.AsistenciaRepository;
import com.ayudamutua.proyecto.repository.EventoRepository;
import com.ayudamutua.proyecto.repository.UsuarioRepository;

@Service
public class AsistenciaService {

	private final AsistenciaRepository asistenciaRepository;
	private final EventoRepository eventoRepository;
	private final UsuarioRepository usuarioRepository;

	public AsistenciaService(AsistenciaRepository asistenciaRepository, EventoRepository eventoRepository,
			UsuarioRepository usuarioRepository) {
		this.asistenciaRepository = asistenciaRepository;
		this.eventoRepository = eventoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public AsistenciaResponseDTO registrarAsistencia(AsistenciaRequestDTO requestDTO) {

		boolean yaExiste = asistenciaRepository.existsByUsuarioIdAndEventoId(requestDTO.getUsuarioId(), requestDTO.getEventoId());
		
		if (yaExiste) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya está registrado en este evento");
		}

		Usuario usuarioEncontrado = usuarioRepository.findById(requestDTO.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		Evento eventoEncontrado = eventoRepository.findById(requestDTO.getEventoId())
				.orElseThrow(() -> new RuntimeException("Evento no encontrado"));

		Asistencia nuevaAsistencia = new Asistencia();
		nuevaAsistencia.setUsuario(usuarioEncontrado);
		nuevaAsistencia.setEvento(eventoEncontrado);

		Asistencia asistenciaGuardada = asistenciaRepository.save(nuevaAsistencia);
		
		return mapearADTO(asistenciaGuardada);

	}

	@Transactional(readOnly = true)
	public List<AsistenciaResponseDTO> obtenerAsistenciasPorEvento(Long eventoId) {

		return asistenciaRepository.findByEventoId(eventoId)
				.stream()
				.map(this::mapearADTO)
				.collect(Collectors.toList());
	}
	private AsistenciaResponseDTO mapearADTO(Asistencia asistencia) {
		AsistenciaResponseDTO dto = new AsistenciaResponseDTO();
		dto.setId(asistencia.getId());
		dto.setUsuarioId(asistencia.getUsuario().getId());
		dto.setEventoId(asistencia.getEvento().getId());
		return dto;
	}
}