package br.edu.opi.manager.student.repositories;

import br.edu.opi.manager.student.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Page<Student> findAllBySchoolId(Long schoolId, Pageable pageable);

	List<Student> findAllBySchoolId(Long schoolId);

	Student findByIdAndSchoolId(Long id, Long schoolId);

	Student findByPersonFullNameAndPersonDateBirth(String fullName, LocalDate dateBirth);

	List<Student> findByPersonAcronymAndPersonFirstNameAndPersonLastNameAndPersonDateBirth(String acronym, String firsName, String lastName, LocalDate dateBirth);

}
