package com.ayudamutua.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayudamutua.proyecto.model.Usuario;
import com.ayudamutua.proyecto.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@GetMapping
	public List<Usuario> obtenerTodosLosUsuarios() {
		return usuarioService.regresarAllUsers();
	}
	
	@PostMapping
	public Usuario crearUsuario(@Valid @RequestBody Usuario nuevoUsuario) {
		return usuarioService.crearUsuario(nuevoUsuario);
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
		return usuarioService.buscarUsuarioPorId(id);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarUsuario(@PathVariable Long id) {
		 usuarioService.borrarUsuario(id);
	}
	
	@PutMapping("/{id}")
	public Usuario usuarioActualizado (@PathVariable Long id, @Valid @RequestBody Usuario actualizacionDeUsuario) {
		actualizacionDeUsuario.setId(id);
		return usuarioService.actualizarUsuario(actualizacionDeUsuario);
	}
}
