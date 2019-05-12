package br.edu.opi.manager.school.repositories;

import br.edu.opi.manager.school.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchoolRepository extends JpaRepository<School, Long> {

	School findByDelegateUsername(String username);

	@Query(value = "SELECT name FROM tb_school where delegate_id = ?1", nativeQuery = true)
	String findSchoolNameByDelegateId(Long delegateId);

}
