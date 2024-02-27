package es.grupo4.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.grupo4.apirest.model.IncidenciasSubtipo;

@Repository
public interface IncidenciasSubtipoRepository extends JpaRepository<IncidenciasSubtipo, Integer>{
	 List<IncidenciasSubtipo> findBySubtipoNombre(String subtipoNombre);
	 //Optional<IncidenciasSubtipo> findBySubtipoNombreAndSubSubtipo(String subtipoNombre, String subtipoSubtipo);
	 List<IncidenciasSubtipo> findByTipoAndSubtipoNombre(String tipo, String subtipoNombre);
}
