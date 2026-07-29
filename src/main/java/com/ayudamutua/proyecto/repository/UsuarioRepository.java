package com.ayudamutua.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ayudamutua.proyecto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}