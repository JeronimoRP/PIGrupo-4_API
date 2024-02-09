package es.luisherrero.apirest1.model;

import java.io.Serializable;
// import java.util.List;

import jakarta.persistence.*;


/**
 * The persistent class for the aulas database table.
 * 
 */
@Entity
@Table(name="aulas")
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int num;

	private String codigo;

	private String descripcion;

	private int planta;

	// private List<Equipo> equipos;

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

	/*
	public List<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Equipo addEquipo(Equipo equipo) {
		getEquipos().add(equipo);
		equipo.setAula(this);

		return equipo;
	}

	public Equipo removeEquipo(Equipo equipo) {
		getEquipos().remove(equipo);
		equipo.setAula(null);

		return equipo;
	}
	*/

}