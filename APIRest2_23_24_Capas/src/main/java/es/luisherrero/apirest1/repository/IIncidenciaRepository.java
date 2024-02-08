package es.luisherrero.apirest1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.luisherrero.apirest1.model.Incidencia;

public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer>{

}
