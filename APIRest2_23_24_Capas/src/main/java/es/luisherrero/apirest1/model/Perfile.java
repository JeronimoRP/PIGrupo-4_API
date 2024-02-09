package es.luisherrero.apirest1.model;

import java.io.Serializable;

import jakarta.persistence.*;


/**
 * The persistent class for the perfiles database table.
 * 
 */
@Entity
@Table(name="perfiles")
public class Perfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int personalId;

	private String dominio;

	private String educantabria;

	private String password;

	private String perfil;

	private Personal personal;

	public Perfile() {
	}

	public int getPersonalId() {
		return this.personalId;
	}

	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}

	public String getDominio() {
		return this.dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getEducantabria() {
		return this.educantabria;
	}

	public void setEducantabria(String educantabria) {
		this.educantabria = educantabria;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

}