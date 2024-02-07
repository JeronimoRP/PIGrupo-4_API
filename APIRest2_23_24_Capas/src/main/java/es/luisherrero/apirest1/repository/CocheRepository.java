package es.luisherrero.apirest1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.luisherrero.apirest1.model.Coche;
import jakarta.transaction.Transactional;

public interface CocheRepository extends JpaRepository<Coche, Integer>{
	//Se incluye un método que busca a todos los coches filtrados por marca
		// Al llamarse findAllBy seguido de un nombre de atributo de entidad, 
		// no tenemos que hacer nada para filtrar, el repositorio lo hace
		public List<Coche> findAllByMarca(String  marca);

		// Se incluye un método de desarrollador
		//para hacer una actualización. En este caso incrementar el precio de un coche
		//aunque se podría hacer de otras formas, se ha hecho usando una consulta JPQL
		// que recibe dos parámetros
		@Transactional
	    @Modifying
	    @Query(value = "UPDATE Coche SET precio=precio+:p WHERE id=:id")
	    public void incCochePrecio(@Param("id") Integer id,@Param("p") Integer p);
			

}
