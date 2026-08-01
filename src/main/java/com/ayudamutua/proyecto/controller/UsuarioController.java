package com.ayudamutua.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayudamutua.proyecto.model.Usuario;
import com.ayudamutua.proyecto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@GetMapping
	public List<Usuario> obtenerTodosLosUsuarios() {
		return usuarioService.regresarAllUsers();
	}
	
}
