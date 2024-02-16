package es.luisherrero.apirest1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.luisherrero.apirest1.model.IncidenciasSubtipo;

@Repository
public interface IIncidenciasSubtipoRepository extends JpaRepository<IncidenciasSubtipo, Integer>{
	List<IncidenciasSubtipo> findBySubtipoNombre(String subtipoNombre);
}
