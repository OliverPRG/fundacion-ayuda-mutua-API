package com.ayudamutua.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ayudamutua.proyecto.model.Usuario;
import com.ayudamutua.proyecto.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Usuario> regresarAllUsers() {
		return usuarioRepository.findAll();
	}
	public Usuario crearUsuario(Usuario nuevoUsuario) {
		if (usuarioRepository.existsByCorreo(nuevoUsuario.getCorreo())) {
			throw new IllegalArgumentException("El correo electronico ingresado ya existe");
		}
		if (usuarioRepository.existsByTelefono(nuevoUsuario.getTelefono())) {
			throw new IllegalArgumentException("El telefono ingresado ya existe");
		}
		String passwordOriginal = nuevoUsuario.getPassword();
		String passwordEncriptado = passwordEncoder.encode(passwordOriginal);
		nuevoUsuario.setPassword(passwordEncriptado);
		
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
