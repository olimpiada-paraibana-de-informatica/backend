package br.edu.opi.manager.school.repositories;

import br.edu.opi.manager.school.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {

	School findByDelegateUsername(String username);

	@Query(value = "SELECT name FROM tb_school WHERE delegate_id = ?1", nativeQuery = true)
	String findSchoolNameByDelegateId(Long delegateId);

	@Query(value = "SELECT s.* FROM tb_school s, tb_delegate_level_notification dln WHERE s.delegate_id = dln.delegate_id and dln.year = ?1", nativeQuery = true)
	List<School> findAllWhoPutStudentsScores(Integer year);

}
