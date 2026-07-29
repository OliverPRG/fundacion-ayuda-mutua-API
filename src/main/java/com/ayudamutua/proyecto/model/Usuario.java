package com.ayudamutua.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "detalles_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String correo;
	private String telefono;
	private String password;
	
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private LocalDate cumpleanos;
	
	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public LocalDate getCumpleanos() {
		return cumpleanos;
	}

	public void setCumpleanos(LocalDate cumpleanos) {
		this.cumpleanos = cumpleanos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCompleto() {
		return primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", correo=" + correo + ", telefono=" + telefono + ", primerNombre=" + primerNombre
				+ ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", cumpleanos=" + cumpleanos + "]";
	}
	
}
