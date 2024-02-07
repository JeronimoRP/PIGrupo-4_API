package es.luisherrero.apirest1.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="coches")
public class Coche {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
	private String marca;
	private String modelo;
	private int precio;
	public Coche(String marca, String modelo, int precio) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
	}
	public Coche() {
		super();
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, marca, modelo, precio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return id == other.id && Objects.equals(marca, other.marca) && Objects.equals(modelo, other.modelo)
				&& precio == other.precio;
	}
	
	
	
}