package br.edu.opi.manager.school.repositories;

import br.edu.opi.manager.school.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {

	School findByDelegateUsername(String username);

}
