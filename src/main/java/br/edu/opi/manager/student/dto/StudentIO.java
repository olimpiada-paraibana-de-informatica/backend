package br.edu.opi.manager.student.dto;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import br.edu.opi.manager.student.model.Student;

/**
 * Mapper to User.
 */
@Component("studentIO")
public class StudentIO {
		
	private ModelMapper modelMapper;
	
	public StudentIO() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addConverter(studentInputConverter);
		this.modelMapper.addConverter(studentOutputConverter);
	}
	
	final Converter<StudentInput, Student> studentInputConverter = new Converter<StudentInput, Student>() {
		@Override
		public Student convert(MappingContext<StudentInput, Student> context) {
			StudentInput input = context.getSource();
			return new Student(input.getName(), 
					input.getDateBirth(), 
					input.getGenre(), 
					input.getDegree());
		}
	};
	
	public Student mapTo(StudentInput studentInput) {
		return this.modelMapper.map(studentInput, Student.class);
	}
	
	public StudentOutput mapTo(Student student) {
		return this.modelMapper.map(student, StudentOutput.class);
	}
		
	final Converter<Student, StudentOutput> studentOutputConverter = new Converter<Student, StudentOutput>() {
		@Override
		public StudentOutput convert(MappingContext<Student, StudentOutput> context) {
			Student input = context.getSource();
			return toStudentOutput(input);
		}
	};
	
	private StudentOutput toStudentOutput(Student student) {
		StudentOutput studentOutput = new StudentOutput();
		studentOutput.setId(student.getId());
		studentOutput.setName(student.getName());
		studentOutput.setDateBirth(student.getDateBirth());
		studentOutput.setDegree(student.getDegree());
		studentOutput.setGenre(student.getGenre());
		
		studentOutput.setEnabled(student.isEnabled());
		return studentOutput;
	}

}
