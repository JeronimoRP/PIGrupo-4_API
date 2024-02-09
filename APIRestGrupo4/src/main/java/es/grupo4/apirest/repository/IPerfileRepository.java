package es.grupo4.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.grupo4.apirest.model.Perfile;

public interface IPerfileRepository extends JpaRepository<Perfile, Integer>{

	Optional<Perfile>  getPersonalByDominio(String dominio);
}
