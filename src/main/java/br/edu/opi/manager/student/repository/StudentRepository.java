package br.edu.opi.manager.student.repository;

import br.edu.opi.manager.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Page<Student> findAllBySchoolId(Long schoolId, Pageable pageable);

	List<Student> findAllBySchoolId(Long schoolId);

	void deleteAllBySchoolId(Long schoolId); // always error

	Student findByIdAndSchoolId(Long id, Long schoolId);

}
