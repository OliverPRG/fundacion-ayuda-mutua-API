package com.ayudamutua.proyecto.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayudamutua.proyecto.dto.LoginRequestDTO;
import com.ayudamutua.proyecto.dto.UsuarioResponseDTO;
import com.ayudamutua.proyecto.model.Usuario;
import com.ayudamutua.proyecto.security.JwtUtil;
import com.ayudamutua.proyecto.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UsuarioService usuarioService;
	@GetMapping
	public List<UsuarioResponseDTO> obtenerTodosLosUsuarios() {
		return usuarioService.regresarAllUsers()
				.stream()
				.map(this::convertirADTO)
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public UsuarioResponseDTO crearUsuario(@Valid @RequestBody Usuario nuevoUsuario) {
		Usuario usuarioGuardado = usuarioService.crearUsuario(nuevoUsuario);
		return convertirADTO(usuarioGuardado);
	}
	
	@GetMapping("/{id}")
	public Optional<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) {
		return usuarioService.buscarUsuarioPorId(id)
				.map(this::convertirADTO);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarUsuario(@PathVariable Long id) {
		 usuarioService.borrarUsuario(id);
	}
	
	@PutMapping("/{id}")
	public UsuarioResponseDTO usuarioActualizado (@PathVariable Long id, @Valid @RequestBody Usuario actualizacionDeUsuario) {
		actualizacionDeUsuario.setId(id);
		Usuario actualizado = usuarioService.actualizarUsuario(actualizacionDeUsuario);
		return convertirADTO(actualizado);
	}
	private UsuarioResponseDTO convertirADTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setPrimerNombre(usuario.getPrimerNombre());
        dto.setSegundoNombre(usuario.getSegundoNombre());
        dto.setPrimerApellido(usuario.getPrimerApellido());
        dto.setSegundoApellido(usuario.getSegundoApellido());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());
        dto.setCumpleanos(usuario.getCumpleanos());
        return dto;
    }
	@PostMapping("/login")
	public ResponseEntity<String> loginUsuario(@Valid @RequestBody LoginRequestDTO loginData) {
		boolean credencialesValidas = usuarioService.validarCredenciales(loginData);
			if (credencialesValidas) {
				String tokenGenerado = jwtUtil.generarToken(loginData.getCorreo());
				return ResponseEntity.ok(tokenGenerado);
			} else {
				return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
			}
	}
}
