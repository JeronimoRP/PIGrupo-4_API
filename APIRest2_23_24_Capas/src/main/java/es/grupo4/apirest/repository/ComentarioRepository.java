package es.grupo4.apirest.repository;

import es.grupo4.apirest.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{

}
