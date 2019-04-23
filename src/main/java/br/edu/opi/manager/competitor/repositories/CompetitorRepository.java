package br.edu.opi.manager.competitor.repositories;

import br.edu.opi.manager.competitor.models.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Long> {

}
