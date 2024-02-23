package es.grupo4.apirest.model;

import java.io.Serializable;

import jakarta.persistence.*;


/**
 * The persistent class for the aulas database table.
 * 
 */
@Entity
@Table(name="aulas")
public class Aula implements Serializable {
	private static final long serialVersionUID=1;
	@Id
	private int num;

	private String codigo;

	private String descripcion;

	private int planta;

	public Aula() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPlanta() {
		return this.planta;
	}

	public void setPlanta(int planta) {
		this.planta = planta;
	}
	
}