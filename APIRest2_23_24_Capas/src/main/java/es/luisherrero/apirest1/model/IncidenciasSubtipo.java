package es.luisherrero.apirest1.model;

import java.io.Serializable;
import java.util.List;

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

	private String tipo;
	
	@OneToMany
	private List<Incidencia> incidencias;

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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Incidencia> getIncidencias() {
		return this.incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public Incidencia addIncidencia(Incidencia incidencia) {
		getIncidencias().add(incidencia);
		incidencia.setIncidenciasSubtipo(this);

		return incidencia;
	}

	public Incidencia removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setIncidenciasSubtipo(null);

		return incidencia;
	}

}