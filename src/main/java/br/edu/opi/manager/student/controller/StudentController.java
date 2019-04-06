package br.edu.opi.manager.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.opi.manager.student.dto.StudentIO;
import br.edu.opi.manager.student.service.StudentService;
import br.edu.opi.manager.utils.RestConstants;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(RestConstants.SCHOOL_URI)
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
	
	
	
}
