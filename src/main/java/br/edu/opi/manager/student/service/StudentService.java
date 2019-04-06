package br.edu.opi.manager.student.service;

import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.student.model.Student;
import br.edu.opi.manager.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends GenericService<Long, Student, StudentRepository> {

    @Override
    public void validateBeforeCreate(Student model) {

    }

    @Override
    public void validateBeforeUpdate(Student model) {

    }

    @Override
    public void validateBeforeDelete(Long aLong) {

    }
}
