package br.edu.opi.manager.student.controllers;

import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.student.dtos.StudentIO;
import br.edu.opi.manager.student.dtos.StudentInput;
import br.edu.opi.manager.student.dtos.StudentOutput;
import br.edu.opi.manager.student.models.Student;
import br.edu.opi.manager.student.services.StudentService;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(RestConstants.STUDENT_URI)
@Api(tags = "Student")
@CrossOrigin
public class StudentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class.getSimpleName());

	private StudentIO studentIO;

	private StudentService studentService;

	@Autowired
	public StudentController(StudentIO studentIO, StudentService studentService) {
		this.studentIO = studentIO;
		this.studentService = studentService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_STUDENT + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Create a Student")
	public ResponseEntity<?> create(@Valid @RequestBody List<StudentInput> studentsInput) {
		LOGGER.info("trying create new students");
		List<Student> students = studentIO.toStudentList(studentsInput);
		studentService.create(students);
		LOGGER.info("students created");
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.INDEX_STUDENT + "')")
	@ApiOperation(value = "Get All Students")
	@GetMapping({"/", ""})
	// @formatter:off
	public Page<StudentOutput> index(
			@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size) {
		LOGGER.info("index students");
		return studentIO.toPage(studentService.index(page, size));
	}
	// @formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_STUDENT + "')")
	@ApiOperation(value = "Get a Student")
	@GetMapping({"/{id}/", "/{id}"})
	// @formatter:off
	public StudentOutput show(
			@PathVariable("id") Long id) {
		LOGGER.info("show student " + id);
		return studentIO.mapTo(studentService.show(id));
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_STUDENT + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Updates a student")
	public ResponseEntity<?> update(
			//@formatter:off
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody StudentInput studentInput) {
		Student student = studentIO.mapTo(studentInput);
		LOGGER.info("trying update student " + student.getName());
		studentService.update(id, student);
		LOGGER.info("student " + student.getName() + " updated");
		return ResponseEntity.noContent().build();
	}

	//@formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.DELETE_STUDENT + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delete a Student")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		LOGGER.info("trying deleting student " + id);
		studentService.delete(id);
		LOGGER.info("student " + id + " deleted");
		return ResponseEntity.ok().build();
	}
	// @formatter:on

}
