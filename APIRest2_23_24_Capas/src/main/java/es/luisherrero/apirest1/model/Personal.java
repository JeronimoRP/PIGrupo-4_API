package es.luisherrero.apirest1.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;


/**
 * The persistent class for the personal database table.
 * 
 */
@Entity
public class Personal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte activo;

	private String apellido1;

	private String apellido2;

	private String cp;

	private String direccion;

	private String dni;

	private String localidad;

	private String nombre;

	private String tlf;

	private List<Comentario> comentarios;

	private List<Departamento> departamentos;

	private List<Incidencia> incidencias1;

	private List<Incidencia> incidencias2;

	private Perfile perfile;

	private Departamento departamento;

	public Personal() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getActivo() {
		return this.activo;
	}

	public void setActivo(byte activo) {
		this.activo = activo;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTlf() {
		return this.tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setPersonal(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setPersonal(null);

		return comentario;
	}

	public List<Departamento> getDepartamentos() {
		return this.departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Departamento addDepartamento(Departamento departamento) {
		getDepartamentos().add(departamento);
		departamento.setPersonal(this);

		return departamento;
	}

	public Departamento removeDepartamento(Departamento departamento) {
		getDepartamentos().remove(departamento);
		departamento.setPersonal(null);

		return departamento;
	}

	public List<Incidencia> getIncidencias1() {
		return this.incidencias1;
	}

	public void setIncidencias1(List<Incidencia> incidencias1) {
		this.incidencias1 = incidencias1;
	}

	public Incidencia addIncidencias1(Incidencia incidencias1) {
		getIncidencias1().add(incidencias1);
		incidencias1.setPersonal1(this);

		return incidencias1;
	}

	public Incidencia removeIncidencias1(Incidencia incidencias1) {
		getIncidencias1().remove(incidencias1);
		incidencias1.setPersonal1(null);

		return incidencias1;
	}

	public List<Incidencia> getIncidencias2() {
		return this.incidencias2;
	}

	public void setIncidencias2(List<Incidencia> incidencias2) {
		this.incidencias2 = incidencias2;
	}

	public Incidencia addIncidencias2(Incidencia incidencias2) {
		getIncidencias2().add(incidencias2);
		incidencias2.setPersonal2(this);

		return incidencias2;
	}

	public Incidencia removeIncidencias2(Incidencia incidencias2) {
		getIncidencias2().remove(incidencias2);
		incidencias2.setPersonal2(null);

		return incidencias2;
	}

	public Perfile getPerfile() {
		return this.perfile;
	}

	public void setPerfile(Perfile perfile) {
		this.perfile = perfile;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}