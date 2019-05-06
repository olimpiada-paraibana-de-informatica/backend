package br.edu.opi.manager.comunication.email.repositories;

import br.edu.opi.manager.comunication.email.models.InterestedNewDelegates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestedNewDelegatesRepository extends JpaRepository<InterestedNewDelegates, Long> {

}
