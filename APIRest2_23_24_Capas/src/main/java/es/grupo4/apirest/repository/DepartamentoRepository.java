package es.grupo4.apirest.repository;

import es.grupo4.apirest.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{
    Optional<Departamento> getDepartamentoByNombre(String nombre);
}
