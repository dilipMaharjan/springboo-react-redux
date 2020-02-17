package com.dmaharjan.springbootreactredux.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmaharjan.springbootreactredux.domain.Project;
import com.dmaharjan.springbootreactredux.service.error.ValidationErrorHandlingService;
import com.dmaharjan.springbootreactredux.services.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ValidationErrorHandlingService validationErrorHandlingService;

	@PostMapping("/projects")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
		ResponseEntity<?> errorMap = validationErrorHandlingService.mapError(bindingResult);
		if (errorMap != null)
			return errorMap;
		projectService.saveOrUpdateProject(project);
		return new ResponseEntity<>(project, HttpStatus.CREATED);
	}

	@GetMapping("/projects/{projectIdentifier}")
	public ResponseEntity<?> getProjectBy(@PathVariable String projectIdentifier) {
		Project project = projectService.findBy(projectIdentifier);
		return new ResponseEntity<>(project, HttpStatus.OK);
	}

	@GetMapping("/projects")
	public Iterable<Project> getAllProjects() {
		return projectService.findAllProjects();
	}

	@DeleteMapping("/projects/{projectIdentifier}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier) {
		projectService.delete(projectIdentifier);
		return new ResponseEntity<>("Project with iddentifier ' " + projectIdentifier + "' is deleted.", HttpStatus.OK);
	}
}
