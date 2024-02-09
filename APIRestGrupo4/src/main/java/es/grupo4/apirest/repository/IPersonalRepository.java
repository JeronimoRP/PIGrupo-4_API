package es.grupo4.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.grupo4.apirest.model.Personal;

public interface IPersonalRepository extends JpaRepository<Personal, Integer>{

	
}
