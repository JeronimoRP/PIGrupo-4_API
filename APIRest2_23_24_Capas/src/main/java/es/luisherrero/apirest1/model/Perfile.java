package es.luisherrero.apirest1.model;

import java.io.Serializable;

import enums.Perfil;
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
	@Column(name = "personal_id")
	private int personalId;

	private String dominio;

	private String educantabria;

	private String password;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;

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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}