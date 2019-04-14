package br.edu.opi.manager.student.controller;

import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.student.dto.StudentIO;
import br.edu.opi.manager.student.dto.StudentInput;
import br.edu.opi.manager.student.dto.StudentOutput;
import br.edu.opi.manager.student.model.Student;
import br.edu.opi.manager.student.service.StudentService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;

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
	@ApiOperation(value = "Create a School", notes = "Also returns a link to retrieve the saved Student in the location header")
	public ResponseEntity<?> create(@Valid @RequestBody StudentInput studentInput) {
		LOGGER.info("trying create new school " + studentInput.getName());
		Student student = studentIO.mapTo(studentInput);
		Student savedSchool = studentService.create(student);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedSchool.getId())
				.toUri();
		LOGGER.info("school " + student.getId() + " create at " + location);
		return ResponseEntity.created(location).build();
	}

	@PreAuthorize("hasAuthority('" + Privilege.INDEX_STUDENT + "')")
	@ApiOperation(value = "Get All Students")
	@GetMapping({"/", ""})
	// @formatter:off
	public Page<StudentOutput> index(
			@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size) {
		LOGGER.info("index schools");
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
