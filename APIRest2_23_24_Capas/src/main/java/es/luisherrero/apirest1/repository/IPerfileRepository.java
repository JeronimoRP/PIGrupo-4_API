package es.luisherrero.apirest1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.luisherrero.apirest1.model.Perfile;

public interface IPerfileRepository extends JpaRepository<Perfile, Integer>{

	Optional<Perfile>  getPersonalByDominio(String dominio);
}
