package com.ayudamutua.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayudamutua.proyecto.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
	
}
