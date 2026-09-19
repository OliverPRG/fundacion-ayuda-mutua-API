package com.ayudamutua.proyecto.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private Key LLAVE_SECRETA = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long TIEMPO_SESION = 7200000;

	public String generarToken(String correo) {

		long miliSegundosActuales = System.currentTimeMillis();
		Date horaImpresion = new Date(miliSegundosActuales);
		Date horaVencimiento = new Date(miliSegundosActuales + TIEMPO_SESION);

		String sesionActiva = Jwts.builder().setSubject(correo).setIssuedAt(horaImpresion)
				.setExpiration(horaVencimiento).signWith(LLAVE_SECRETA).compact();
		return sesionActiva;
	}

	public String extraerCorreo(String token) {
		return Jwts.parserBuilder().setSigningKey(LLAVE_SECRETA).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validarToken(String token) {

		try {
			extraerCorreo(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
