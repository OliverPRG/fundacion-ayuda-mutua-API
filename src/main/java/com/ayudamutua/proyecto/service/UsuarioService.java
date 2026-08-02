package com.ayudamutua.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayudamutua.proyecto.model.Usuario;
import com.ayudamutua.proyecto.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> regresarAllUsers() {
		return usuarioRepository.findAll();
	}
	public Usuario crearUsuario(Usuario nuevoUsuario) {
		return usuarioRepository.save(nuevoUsuario);
	}
	public Optional<Usuario> buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id);
	}
	public void borrarUsuario(Long id) {
		 usuarioRepository.deleteById(id);
	}
	public Usuario actualizarUsuario(Usuario usuarioActualizado) {
		return usuarioRepository.save(usuarioActualizado);
	}
}
