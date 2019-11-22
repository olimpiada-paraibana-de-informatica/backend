package br.edu.opi.manager.olympiad.repositories;

import br.edu.opi.manager.olympiad.models.OpiDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiDatesRepository extends JpaRepository<OpiDates, Long> {

}
