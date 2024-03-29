package es.grupo4.apirest.model;

import java.io.Serializable;
import java.util.Date;

import es.grupo4.apirest.enums.TipoEquipo;
import jakarta.persistence.*;


/**
 * The persistent class for the equipos database table.
 * 
 */
@Entity
@Table(name="equipos")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte baja;

	private String descripcion;

	private String etiqueta;

	private Date fechaAdquisicion;

	private String marca;

	private String modelo;

	private int puesto;

	@Enumerated(EnumType.STRING)
	private TipoEquipo tipoEquipo;

	@ManyToOne
	private Aula aula;

	public Equipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getBaja() {
		return this.baja;
	}

	public void setBaja(byte baja) {
		this.baja = baja;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEtiqueta() {
		return this.etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public Date getFechaAdquisicion() {
		return this.fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getPuesto() {
		return this.puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public TipoEquipo getTipoEquipo() {
		return tipoEquipo;
	}

	public void setTipoEquipo(TipoEquipo tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
	}

	public Aula getAula() {
		return this.aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

}