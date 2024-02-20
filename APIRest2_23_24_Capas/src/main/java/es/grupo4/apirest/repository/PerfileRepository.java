package es.grupo4.apirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.grupo4.apirest.model.Perfile;

@Repository
public interface PerfileRepository extends JpaRepository<Perfile, Integer>{

	Optional<Perfile>  getPersonalByDominio(String dominio);
}
