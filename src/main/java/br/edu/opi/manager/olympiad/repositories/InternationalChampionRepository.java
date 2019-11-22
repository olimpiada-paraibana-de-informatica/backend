package br.edu.opi.manager.olympiad.repositories;

import br.edu.opi.manager.olympiad.models.InternationalChampion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;

@Repository
public interface InternationalChampionRepository extends JpaRepository<InternationalChampion, Long> {

	@OrderBy("year ASC")
	List<InternationalChampion> findAll();

}
