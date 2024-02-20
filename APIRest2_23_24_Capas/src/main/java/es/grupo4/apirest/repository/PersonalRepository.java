package es.grupo4.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.grupo4.apirest.model.Personal;

import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer>{

	Optional<Personal> findByNombreAndApellido1AndApellido2(String nombre, String apellido1, String apellido2);
}
