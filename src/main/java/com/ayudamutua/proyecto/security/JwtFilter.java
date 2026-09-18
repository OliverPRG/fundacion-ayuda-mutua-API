package com.ayudamutua.proyecto.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// TODO 1: Extrae el texto de la cabecera "Authorization"
		String headerAutorizacion = request.getHeader("Authorization");

		String token = null;
		String correo = null;

		// TODO 2: Comprueba si el header existe (no es null) Y si empieza con "Bearer "
		if (headerAutorizacion != null && headerAutorizacion.startsWith("Bearer ")) {
			
			headerAutorizacion.substring(7);
			// TODO 3: Recorta la palabra "Bearer " (los primeros 7 caracteres) para dejar solo el token
			token = headerAutorizacion.substring(7);

			
			try {
				// TODO 4: Usa tu jwtUtil para extraer el correo escondido dentro del token
				correo = jwtUtil.extraerCorreo(token);
			} catch (Exception e) {
				System.out.println("Token inválido o expirado");
			}
		}

		// (Aún nos falta la parte de registrar al usuario, lo haremos en el siguiente paso)

		// TODO 5: Al final de todo, dile al filtro que deje continuar la petición hacia el Controlador
		// ...
		filterChain.doFilter(request, response);
	}
}