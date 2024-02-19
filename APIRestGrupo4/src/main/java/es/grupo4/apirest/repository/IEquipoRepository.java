package es.grupo4.apirest.repository;

import es.grupo4.apirest.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEquipoRepository extends JpaRepository<Equipo, Integer> {
    Optional<Equipo> getEquipoByEtiqueta(String etiqueta);
}
