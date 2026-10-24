package com.ayudamutua.proyecto.dto;

public class AsistenciaResponseDTO {

	private Long id;
	private Long usuarioId;
	private Long eventoId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getEventoId() {
		return eventoId;
	}

	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}
}
