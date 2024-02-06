package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Aula;

@Repository
public interface IAulaRepository extends JpaRepository<Aula, Integer> {

	
	
}
