package com.ayudamutua.proyecto.model;

public class Usuario {
	
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String correo;
	private String telefono;
	private String password;
	private Long id;
	
	private String primerNombre;
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
		return "Usuario [segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", correo=" + correo + ", telefono=" + telefono + ", id=" + id + ", primerNombre="
				+ primerNombre + "]";
	}
	
}
