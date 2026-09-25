package com.ayudamutua.proyecto.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collections;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String headerAutorizacion = request.getHeader("Authorization");

		String token = null;
		String correo = null;

		if (headerAutorizacion != null && headerAutorizacion.startsWith("Bearer ")) {

			token = headerAutorizacion.substring(7);

			try {
				correo = jwtUtil.extraerCorreo(token);
			} catch (Exception e) {
				System.out.println("Token inválido o expirado");
			}
		}
		if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			if (jwtUtil.validarToken(token)) {

				String rol = jwtUtil.extraerRol(token);				
				
				List<GrantedAuthority> permisos = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol));
				
				
				UsernamePasswordAuthenticationToken registroDeEntrada = new UsernamePasswordAuthenticationToken(correo, null, permisos);				
				
				registroDeEntrada.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(registroDeEntrada);
			}
		}

		filterChain.doFilter(request, response);
	}
}