package com.ayudamutua.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayudamutua.proyecto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByCorreo (String correo);
	boolean existsByTelefono (String telefono);
	Optional<Usuario> findByCorreo(String correo);
}