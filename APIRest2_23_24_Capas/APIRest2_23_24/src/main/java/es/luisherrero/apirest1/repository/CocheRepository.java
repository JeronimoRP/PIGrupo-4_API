package es.luisherrero.apirest1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.luisherrero.apirest1.model.Coche;

public interface CocheRepository extends JpaRepository<Coche, Integer>{

}
