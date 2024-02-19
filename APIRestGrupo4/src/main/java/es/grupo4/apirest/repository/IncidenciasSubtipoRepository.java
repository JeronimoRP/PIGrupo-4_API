package es.grupo4.apirest.repository;

import es.grupo4.apirest.model.IncidenciasSubtipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncidenciasSubtipoRepository extends JpaRepository<IncidenciasSubtipo, Integer> {
    Optional<IncidenciasSubtipo> getSubtipoByNombre(String nombre);
}
