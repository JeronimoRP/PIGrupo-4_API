package es.grupo4.apirest.model;

import java.io.Serializable;

import jakarta.persistence.*;


/**
 * The persistent class for the departamentos database table.
 * 
 */
@Entity
@Table(name="departamentos")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte activo;

	private String cod;

	private String nombre;

	private int jefedep_id;

	public Departamento() {
	}

	public int getInt_() {
		return this.id;
	}

	public void setInt_(int id) {
		this.id = id;
	}

	public byte getActivo() {
		return this.activo;
	}

	public void setActivo(byte activo) {
		this.activo = activo;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getJefedep_id() {
		return jefedep_id;
	}

	public void setJefedep_id(int jefedep_id) {
		this.jefedep_id = jefedep_id;
	}

}