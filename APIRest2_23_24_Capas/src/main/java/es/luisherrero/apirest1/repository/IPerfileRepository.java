package es.luisherrero.apirest1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.luisherrero.apirest1.model.Perfile;

@Repository
public interface IPerfileRepository extends JpaRepository<Perfile, Integer>{

	Optional<Perfile>  getPersonalByDominio(String dominio);
}
