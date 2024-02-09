package es.luisherrero.apirest1.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;


/**
 * The persistent class for the comentarios database table.
 * 
 */
@Entity
@Table(name="comentarios")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	private String adjuntoUrl;

	private Date fechahora;

	private String texto;

	@ManyToOne
	private Incidencia incidencia;

	@ManyToOne
	private Personal personal;

	public Comentario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdjuntoUrl() {
		return this.adjuntoUrl;
	}

	public void setAdjuntoUrl(String adjuntoUrl) {
		this.adjuntoUrl = adjuntoUrl;
	}

	public Date getFechahora() {
		return this.fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Incidencia getIncidencia() {
		return this.incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

}