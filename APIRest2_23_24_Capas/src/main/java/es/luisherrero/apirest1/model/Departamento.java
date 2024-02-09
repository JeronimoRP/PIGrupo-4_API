package es.luisherrero.apirest1.model;

import java.io.Serializable;
import java.util.List;

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
	private int int_;

	private byte activo;

	private String cod;

	private String nombre;

	private Personal personal;

	private List<Personal> personals;

	public Departamento() {
	}

	public int getInt_() {
		return this.int_;
	}

	public void setInt_(int int_) {
		this.int_ = int_;
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

	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public List<Personal> getPersonals() {
		return this.personals;
	}

	public void setPersonals(List<Personal> personals) {
		this.personals = personals;
	}

	public Personal addPersonal(Personal personal) {
		getPersonals().add(personal);
		personal.setDepartamento(this);

		return personal;
	}

	public Personal removePersonal(Personal personal) {
		getPersonals().remove(personal);
		personal.setDepartamento(null);

		return personal;
	}

}