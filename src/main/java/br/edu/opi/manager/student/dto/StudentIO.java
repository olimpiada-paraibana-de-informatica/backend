package br.edu.opi.manager.student.dto;

import br.edu.opi.manager.school.dto.SchoolOutput;
import br.edu.opi.manager.school.model.School;
import br.edu.opi.manager.student.model.Student;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Mapper to User.
 */
@Component("studentIO")
public class StudentIO {

	private ModelMapper modelMapper;

	final Converter<StudentInput, Student> studentInputConverter = new Converter<StudentInput, Student>() {
		@Override
		public Student convert(MappingContext<StudentInput, Student> context) {
			StudentInput input = context.getSource();
			return new Student(
					input.getName(),
					input.getDateBirth(),
					input.getGenre(),
					new School(input.getSchoolId()));
		}
	};

	final Converter<Student, StudentOutput> studentOutputConverter = new Converter<Student, StudentOutput>() {
		@Override
		public StudentOutput convert(MappingContext<Student, StudentOutput> context) {
			Student input = context.getSource();
			return toStudentOutput(input);
		}
	};

	public StudentIO() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addConverter(studentInputConverter);
		this.modelMapper.addConverter(studentOutputConverter);
	}

	public Student mapTo(StudentInput studentInput) {
		return this.modelMapper.map(studentInput, Student.class);
	}

	public StudentOutput mapTo(Student student) {
		return this.modelMapper.map(student, StudentOutput.class);
	}

	public List<StudentOutput> toList(List<Student> source) {
		Type dest = new TypeToken<List<StudentOutput>>() {}.getType();
		return modelMapper.map(source, dest);
	}

	public List<Student> toStudentList(List<StudentInput> source) {
		Type dest = new TypeToken<List<Student>>() {}.getType();
		return modelMapper.map(source, dest);
	}

	public Page<StudentOutput> toPage(Page<Student> source) {
		List<StudentOutput> list = toList(source.getContent());
		return new PageImpl<>(list, source.getPageable(), source.getTotalElements());
	}

	private StudentOutput toStudentOutput(Student student) {
		StudentOutput studentOutput = new StudentOutput();
		studentOutput.setId(student.getId());
		studentOutput.setName(student.getName());
		studentOutput.setDateBirth(student.getDateBirth());
		studentOutput.setGenre(student.getGenre());
		studentOutput.setEnabled(student.isEnabled());
		return studentOutput;
	}

}
