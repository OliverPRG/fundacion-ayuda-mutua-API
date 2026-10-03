package com.ayudamutua.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayudamutua.proyecto.model.Asistencia;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

	List<Asistencia> findByEventoId(Long eventoId);

	boolean existsByUsuarioIdAndEventoId(Long usuarioId, Long eventoId);
}
