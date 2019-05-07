package br.edu.opi.manager.places.repositories;

import br.edu.opi.manager.places.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
