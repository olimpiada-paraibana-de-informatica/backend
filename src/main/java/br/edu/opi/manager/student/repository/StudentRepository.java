package br.edu.opi.manager.student.repository;

import br.edu.opi.manager.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Page<Student> findAllBySchoolId(Long schoolId, Pageable pageable);

	Student findByIdAndSchoolId(Long id, Long schoolId);

}
