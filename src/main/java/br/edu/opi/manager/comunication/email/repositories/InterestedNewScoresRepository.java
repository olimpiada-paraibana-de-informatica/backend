package br.edu.opi.manager.comunication.email.repositories;

import br.edu.opi.manager.comunication.email.models.InterestedNewScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestedNewScoresRepository extends JpaRepository<InterestedNewScores, Long> {

}
