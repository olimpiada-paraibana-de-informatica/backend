package br.edu.opi.manager.school.repository;

import br.edu.opi.manager.school.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
