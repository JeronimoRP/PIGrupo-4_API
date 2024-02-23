package es.grupo4.apirest.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;


/**
 * The persistent class for the incidencias database table.
 * 
 */
@Entity
@Table(name="incidencias")
public class Incidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int num;

	private String adjuntoUrl;

	private String descripcion;

	private String estado;

	private Date fechaCierre;

	private Date fechaCreacion;

	private String tipo;

	@ManyToOne
	private Equipo equipo;

	@ManyToOne
	@JoinColumn(name="subtipo_id")
	private IncidenciasSubtipo incidenciasSubtipo;

	@ManyToOne
	@JoinColumn(name="creador_id")
	private Personal personal1;

	@ManyToOne
	@JoinColumn(name="responsable_id")
	private Personal personal2;

	public Incidencia() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAdjuntoUrl() {
		return this.adjuntoUrl;
	}

	public void setAdjuntoUrl(String adjuntoUrl) {
		this.adjuntoUrl = adjuntoUrl;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public IncidenciasSubtipo getIncidenciasSubtipo() {
		return this.incidenciasSubtipo;
	}

	public void setIncidenciasSubtipo(IncidenciasSubtipo incidenciasSubtipo) {
		this.incidenciasSubtipo = incidenciasSubtipo;
	}

	public Personal getPersonal1() {
		return this.personal1;
	}

	public void setPersonal1(Personal personal1) {
		this.personal1 = personal1;
	}

	public Personal getPersonal2() {
		return this.personal2;
	}

	public void setPersonal2(Personal personal2) {
		this.personal2 = personal2;
	}

}