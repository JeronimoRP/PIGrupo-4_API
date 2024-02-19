package es.grupo4.apirest.repository;

import es.grupo4.apirest.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDepartamentoRepository extends JpaRepository<Departamento, Integer> {
    Optional<Departamento> getDepartamentoByNombre(String nombre);
}
