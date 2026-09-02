package com.ayudamutua.proyecto.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "detalles_usuario")
@JsonPropertyOrder({"id", "primerNombre", "segundoNombre", "primerApellido", "segundoApellido", "correo", "telefono", "cumpleanos"})
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message="El formato del correo electronico no es válido")
	@NotBlank(message="El correo electronico es obligatorio")
	private String correo;
	
	@Pattern(regexp = "^\\d{10}$", message = "El teléfono debe contener exactamente 10 números")
	@NotNull(message="Introduce un numero de celular valido")
	private String telefono;
	
	@Pattern(
			regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&#.]).+$",
			message = "La contraseña debe tener al menos 6 caracteres, una letra en mayuscula, números y almenos un caracter especial"
			)
	@NotBlank(message = "Crea una contraseña valida")
	@Size(min = 6, max = 255)
	private String password;
	
	@NotBlank(message="Introduce tu primer nombre")
	private String primerNombre;
	
	private String segundoNombre;
	
	@NotBlank(message="Introduce tu primer apellido")
	private String primerApellido;
	
	@NotBlank(message="Introduce tu segundo apellido")
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
