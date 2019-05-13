package br.edu.opi.manager.competitor.repositories;

import br.edu.opi.manager.competitor.models.Competitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Long> {

	List<Competitor> findAllByStudentIdAndYear(Long id, Integer year);

	@Query(value = "SELECT c.* FROM tb_competitor c, tb_student st, tb_school s, tb_user u WHERE u.username = ?1 AND u.id = s.delegate_id AND s.id = st.school_id AND st.id = ?2 AND s.id = c.student_id AND c.year = ?3", nativeQuery = true)
	List<Competitor> findByDelegateSchoolAndStudentAndYear(String delegatePrincipal, Long studentId, Integer year);

	List<Competitor> findAllByStudentSchoolDelegateUsernameAndStudentIdAndYear(String delegatePrincipal, Long studentId, Integer year);

	Page<Competitor> findAllByStudentSchoolId(Long schoolId, Pageable pageable);

	Competitor findByIdAndStudentSchoolId(Long id, Long schoolId);

	@Query(value = "SELECT c.* FROM tb_competitor c WHERE c.year = ?1 ORDER BY c.score_level_one DESC LIMIT (SELECT CAST(COUNT(*) * ?2 AS INTEGER) AS PERCENTILE FROM tb_competitor)", nativeQuery = true)
	Set<Competitor> findAllClassifieds(int year, double percentageConsidered);

}
