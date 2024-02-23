package es.grupo4.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.grupo4.apirest.model.Equipo;

import java.util.Optional;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{
    Optional<Equipo> getEquipoByEtiqueta(String etiqueta);
}
