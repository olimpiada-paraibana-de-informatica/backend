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
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(RestConstants.STUDENT_DELEGATE_URI)
@Api(tags = "Student")
@CrossOrigin
public class StudentDelegateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentDelegateController.class.getSimpleName());

	private StudentIO studentIO;

	private StudentService studentService;

	@Autowired
	public StudentDelegateController(StudentIO studentIO, StudentService studentService) {
		this.studentIO = studentIO;
		this.studentService = studentService;
	}

	@PreAuthorize("hasAuthority('" + Privilege.CREATE_ASSOCIATED_STUDENT + "')")
	@PostMapping({"/", ""})
	@ApiOperation(value = "Delegate Creates a Student")
	public ResponseEntity<?> create(@Valid @RequestBody List<StudentInput> studentsInput, Principal principal) {
		LOGGER.info("delegate trying create new associated students");
		List<Student> students = studentIO.toStudentList(studentsInput);
		studentService.create(students, principal.getName());
		LOGGER.info("associated students created");
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.INDEX_ASSOCIATED_STUDENT + "')")
	@ApiOperation(value = "Delegate Gets All Students")
	@GetMapping({"/", ""})
	// @formatter:off
	public Page<StudentOutput> index(
			@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size,
			Principal principal) {
		LOGGER.info("delegate " + principal.getName() + " index associated students");
		return studentIO.toPage(studentService.index(page, size, principal.getName()));
	}
	// @formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.SHOW_ASSOCIATED_STUDENT + "')")
	@ApiOperation(value = "Delegate Gets a Student")
	@GetMapping({"/{id}/", "/{id}"})
	public StudentOutput show(@PathVariable("id") Long id, Principal principal) {
		LOGGER.info("delegate " + principal.getName() + " show associated student " + id);
		return studentIO.mapTo(studentService.show(id, principal.getName()));
	}

	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_ASSOCIATED_STUDENT + "')")
	@PutMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delegate Updates a student")
	//@formatter:off
	public ResponseEntity<?> update(
			@Min(value = 1) @PathVariable("id") Long id,
			@Valid @RequestBody StudentInput studentInput,
			Principal principal) {
		Student student = studentIO.mapTo(studentInput);
		LOGGER.info("delegate " + principal.getName() + " trying update associated student " + id);
		studentService.update(id, student, principal.getName());
		LOGGER.info("associated student " + id + " updated");
		return ResponseEntity.noContent().build();
	}
	//@formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.DELETE_ASSOCIATED_STUDENT + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delegate Deletes a Student")
	public ResponseEntity<?> delete(@PathVariable("id") Long id, Principal principal) {
		LOGGER.info("delegate " + principal.getName() + "trying deleting associated student " + id);
		studentService.delete(id, principal.getName());
		LOGGER.info("associated student " + id + " deleted");
		return ResponseEntity.ok().build();
	}
	// @formatter:on

}
