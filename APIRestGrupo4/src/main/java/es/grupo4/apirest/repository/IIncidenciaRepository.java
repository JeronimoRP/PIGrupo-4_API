package es.grupo4.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.grupo4.apirest.model.Incidencia;

public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer>{

}
