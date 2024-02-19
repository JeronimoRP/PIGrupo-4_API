package es.luisherrero.apirest1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.luisherrero.apirest1.model.Personal;

@Repository
public interface IPersonalRepository extends JpaRepository<Personal, Integer>{

	Personal findByNombreAndApellido1AndApellido2(String nombre, String apellido1, String apellido2);
}
