package br.edu.opi.manager.competitor.controllers;

import br.edu.opi.manager.competitor.dtos.CompetitorIO;
import br.edu.opi.manager.competitor.services.CompetitorService;
import br.edu.opi.manager.project_patterns.models.user.Privilege;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestConstants.COMPETITOR_URI)
@Api(tags = "Competitor")
@CrossOrigin
public class CompetitorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompetitorController.class.getSimpleName());

	private CompetitorIO competitorIO;

	private CompetitorService competitorService;

	@Autowired
	public CompetitorController(CompetitorIO competitorIO, CompetitorService competitorService) {
		this.competitorIO = competitorIO;
		this.competitorService = competitorService;
	}

//	@PreAuthorize("hasAuthority('" + Privilege.CREATE_COMPETITOR + "')")
//	@PostMapping({"/", ""})
//	@ApiOperation(value = "Create a Competitor")
//	public ResponseEntity<?> create(@Valid @RequestBody List<CompetitorInput> competitorInput) {
//		LOGGER.info("trying create new students");
//		List<Student> students = studentIO.toStudentList(studentsInput);
//		studentService.create(students);
//		LOGGER.info("students created");
//		return ResponseEntity.noContent().build();
//	}
//
//	@PreAuthorize("hasAuthority('" + Privilege.INDEX_STUDENT + "')")
//	@ApiOperation(value = "Get All Students")
//	@GetMapping({"/", ""})
//	// @formatter:off
//	public Page<StudentOutput> index(
//			@RequestParam(required = false, name = "page") Integer page,
//			@RequestParam(required = false, name = "size") Integer size) {
//		LOGGER.info("index students");
//		return studentIO.toPage(studentService.index(page, size));
//	}
//	// @formatter:on
//
//	@PreAuthorize("hasAuthority('" + Privilege.SHOW_STUDENT + "')")
//	@ApiOperation(value = "Get a Student")
//	@GetMapping({"/{id}/", "/{id}"})
//	// @formatter:off
//	public StudentOutput show(
//			@PathVariable("id") Long id) {
//		LOGGER.info("show student " + id);
//		return studentIO.mapTo(studentService.show(id));
//	}
//
//	@PreAuthorize("hasAuthority('" + Privilege.UPDATE_COMPETITOR + "')")
//	@PutMapping({"/{id}/", "/{id}"})
//	@ApiOperation(value = "Updates a student")
//	public ResponseEntity<?> update(
//			//@formatter:off
//			@Min(value = 1) @PathVariable("id") Long id,
//			@Valid @RequestBody StudentInput studentInput) {
//		Student student = studentIO.mapTo(studentInput);
//		LOGGER.info("trying update student " + student.getName());
//		studentService.update(id, student);
//		LOGGER.info("student " + student.getName() + " updated");
//		return ResponseEntity.noContent().build();
//	}

	//@formatter:on

	@PreAuthorize("hasAuthority('" + Privilege.DELETE_COMPETITOR + "')")
	@DeleteMapping({"/{id}/", "/{id}"})
	@ApiOperation(value = "Delete a Competitor")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		LOGGER.info("trying deleting student " + id);
		competitorService.delete(id);
		LOGGER.info("competitor " + id + " deleted");
		return ResponseEntity.ok().build();
	}
	// @formatter:on

}
