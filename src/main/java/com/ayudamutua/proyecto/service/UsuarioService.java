package com.ayudamutua.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayudamutua.proyecto.dto.LoginRequestDTO;
import com.ayudamutua.proyecto.dto.UsuarioRequestDTO;
import com.ayudamutua.proyecto.model.Usuario;
import com.ayudamutua.proyecto.repository.UsuarioRepository;


@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional(readOnly = true)
	public List<Usuario> regresarAllUsers() {
		return usuarioRepository.findAll();
	}
	@Transactional
	public Usuario crearUsuario(UsuarioRequestDTO requestDTO) {
		if (usuarioRepository.existsByCorreo(requestDTO.getCorreo())) {
			throw new IllegalArgumentException("El correo electronico ingresado ya existe");
		}
		if (usuarioRepository.existsByTelefono(requestDTO.getTelefono())) {
			throw new IllegalArgumentException("El telefono ingresado ya existe");
		}

		Usuario nuevoUsuario = new Usuario();

		nuevoUsuario.setPrimerNombre(requestDTO.getPrimerNombre());
		nuevoUsuario.setSegundoNombre(requestDTO.getSegundoNombre());
		nuevoUsuario.setPrimerApellido(requestDTO.getPrimerApellido());
		nuevoUsuario.setSegundoApellido(requestDTO.getSegundoApellido());
		nuevoUsuario.setCorreo(requestDTO.getCorreo());
		nuevoUsuario.setTelefono(requestDTO.getTelefono());
		nuevoUsuario.setCumpleanos(requestDTO.getCumpleanos());

		nuevoUsuario.setRol("USER");
		String passwordEncriptado = passwordEncoder.encode(requestDTO.getPassword());
		nuevoUsuario.setPassword(passwordEncriptado);

		return usuarioRepository.save(nuevoUsuario);
	}
	@Transactional(readOnly = true)
	public Optional<Usuario> buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id);
	}
	@Transactional(readOnly = true)
	public Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
		return usuarioRepository.findByCorreo(correo);
	}
	@Transactional
	public void borrarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	@Transactional
	public Usuario actualizarUsuario(Usuario usuarioActualizado) {
		return usuarioRepository.save(usuarioActualizado);
	}
	@Transactional(readOnly = true)
	public boolean validarCredenciales(LoginRequestDTO loginData) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(loginData.getCorreo());
		if (usuarioOpt.isEmpty()) {
			return false;
		}
		Usuario usuarioReal = usuarioOpt.get();
		return passwordEncoder.matches(loginData.getPassword(), usuarioReal.getPassword());
	}
}
