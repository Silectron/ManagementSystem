package managementsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import managementsystem.dto.LeadDto;
import managementsystem.model.Lead;
import managementsystem.dto.ProjectDto;
import managementsystem.dto.EmployeeDto;
import managementsystem.dto.AssignmentDto;
import managementsystem.model.Project;
import managementsystem.model.Employee;
import managementsystem.model.Assignment;
import managementsystem.service.ProjectAssignmentService;

@CrossOrigin(origins = "*")
@RestController
public class ProjectAssignmentRestController {

	@Autowired
	private ProjectAssignmentService service;

	
}