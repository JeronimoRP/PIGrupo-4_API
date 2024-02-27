package es.grupo4.apirest.model;

import java.io.Serializable;

import es.grupo4.apirest.enums.Tipo;
import jakarta.persistence.*;


/**
 * The persistent class for the incidencias_subtipos database table.
 * 
 */
@Entity
@Table(name="incidencias_subtipos")
public class IncidenciasSubtipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String subSubtipo;

	private String subtipoNombre;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	

	public IncidenciasSubtipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubSubtipo() {
		return this.subSubtipo;
	}

	public void setSubSubtipo(String subSubtipo) {
		this.subSubtipo = subSubtipo;
	}

	public String getSubtipoNombre() {
		return this.subtipoNombre;
	}

	public void setSubtipoNombre(String subtipoNombre) {
		this.subtipoNombre = subtipoNombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}