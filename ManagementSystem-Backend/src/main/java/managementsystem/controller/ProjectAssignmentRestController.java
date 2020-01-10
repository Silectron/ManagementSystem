package managementsystem.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	// POST Mappings

	// Example REST call:
	// http://localhost:8088/employees/John
	@PostMapping(value = { "/employees/{name}", "/employees/{name}/" })
	public EmployeeDto createEmployee(@PathVariable("name") String name) throws IllegalArgumentException {
		
		Employee employee = service.createEmployee(name);
		return convertToDto(employee);
	}

	// LeadDto
	@PostMapping(value = { "/leads/{name}", "/leads/{name}/" })
	public LeadDto createLead(@PathVariable("name") String name) throws IllegalArgumentException {
		Lead lead = service.createLead(name);
		return convertToDto(lead);
	}
	
	// Example REST call:
	// http://localhost:8080/projects/testproject?status=ongoing&startDate=2020-01-01&endDate=2020-04-01&client=Company
	// Project Dto
	@PostMapping(value = { "/projects/{name}", "/projects/{name}/" })
	public ProjectDto createProject(@PathVariable("name") String name, @RequestParam String status, @RequestParam Date startDate,
			@RequestParam Date endDate,
			@RequestParam String client) throws IllegalArgumentException {
		
		Project project = service.createProject(name, status, startDate, endDate, client);
		return convertToDto(project);
	}

	@PostMapping(value = { "/assign", "/assign/" })
	public AssignmentDto assignEmployeeToProject(@RequestParam(name = "employee") EmployeeDto eDto,
			@RequestParam(name = "project") ProjectDto pDto) throws IllegalArgumentException {
		
		// Both the employee and the project are identified by their names
		Employee e = service.getEmployee(eDto.getName());
		Project p = service.getProject(pDto.getName());

		Assignment a = service.assign(e, p);
		return convertToDto(a, e, p);
	}

	// @formatter:off
	@PostMapping(value = { "/leadprojects", "/leadprojects/" })
	public LeadDto assignLeadForProject(@RequestParam(name = "lead") LeadDto lDto,
			@RequestParam(name = "project") ProjectDto pDto) throws IllegalArgumentException {

		// Both the lead and the project are identified by their names
		Lead l = service.getLead(lDto.getName());
		Project p = service.getProject(pDto.getName());

		List<Project> Projects = new ArrayList<Project>();
		for (Project projects : l.getProjects()) {
			Projects.add(projects);
		}
		Projects.add(p);

		List<Lead> Leads = new ArrayList<Lead>();
		for (Lead pro : p.getLeads()) {
			Leads.add(pro);
		}
		Leads.add(l);

		l.setProjects(Projects);
		p.setLeads(Leads);

		return convertToDto(l);
	}

	// GET Mappings

	@GetMapping(value = { "/projects", "/projects/" })
	public List<ProjectDto> getAllProjects() {
		List<ProjectDto> projectDtos = new ArrayList<>();
		for (Project project : service.getAllProjects()) {
			projectDtos.add(convertToDto(project));
		}
		return projectDtos;
	}

	// Example REST call:
	// http://localhost:8088/projects/employee/JohnDoe
	@GetMapping(value = { "/projects/employee/{name}", "/projects/employee/{name}/" })
	public List<ProjectDto> getProjectsOfEmployee(@PathVariable("name") EmployeeDto eDto) {
		Employee e = convertToDomainObject(eDto);
		return createAssignedProjectDtosForEmployee(e);
	}

	@GetMapping(value = { "/employees/{name}", "/employees/{name}/" })
	public EmployeeDto getEmployeeByName(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(service.getEmployee(name));
	}

	@GetMapping(value = { "/assignments", "/assignments/" })
	public AssignmentDto getAssignment(@RequestParam(name = "employee") EmployeeDto eDto,
			@RequestParam(name = "project") ProjectDto pDto) throws IllegalArgumentException {
		// Both the employee and the project are identified by their names
		Employee e = service.getEmployee(eDto.getName());
		Project p = service.getProject(pDto.getName());

		Assignment a = service.getAssignmentByEmployeeAndProject(e, p);
		return convertToDtoWithoutEmployee(a);
	}

	@GetMapping(value = { "/assignments/employee/{name}", "/assignments/employee/{name}/" })
	public List<AssignmentDto> getAssignmentsForEmployee(@PathVariable("name") EmployeeDto eDto)
			throws IllegalArgumentException {
		// Both the employee and the project are identified by their names
		Employee e = service.getEmployee(eDto.getName());

		return createAssignmentDtosForEmployee(e);
	}

	@GetMapping(value = { "/employees", "/employees/" })
	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> employees = new ArrayList<>();
		for (Employee employee : service.getAllEmployees()) {
			employees.add(convertToDto(employee));
		}
		return employees;
	}

	@GetMapping(value = { "/projects/{name}", "/projects/{name}/" })
	public ProjectDto getProjectByName(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(service.getProject(name));
	}

	// Model - DTO conversion methods

	private ProjectDto convertToDto(Project p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Project!");
		}
		ProjectDto projectDto = new ProjectDto(p.getName(), p.getStatus(), p.getStartDate(), p.getEndDate(), p.getClient());
		
		return projectDto;
	}

	private EmployeeDto convertToDto(Employee e) {
		if (e == null) {
			throw new IllegalArgumentException("There is no such Employee!");
		}
		EmployeeDto employeeDto = new EmployeeDto(e.getName());
		employeeDto.setProjectsAssigned(createAssignedProjectDtosForEmployee(e));
		return employeeDto;
	}

	private LeadDto convertToDto(Lead l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Lead!");
		}
		LeadDto leadDto = new LeadDto(l.getName());
		List<ProjectDto> ProjectDtos = new ArrayList<ProjectDto>();
		if(l.getProjects() != null) {
			for (Project p : l.getProjects()) {
				ProjectDtos.add(convertToDto(p));
			}
			leadDto.setLeads(ProjectDtos);
		}

		return leadDto;
	}

	// DTOs for assignments
	private AssignmentDto convertToDto(Assignment a, Employee e, Project p) {
		ProjectDto pDto = convertToDto(p);
		EmployeeDto eDto = convertToDto(e);
		return new AssignmentDto(eDto, pDto);
	}

	private AssignmentDto convertToDto(Assignment a) {
		ProjectDto pDto = convertToDto(a.getProject());
		EmployeeDto eDto = convertToDto(a.getEmployee());
		AssignmentDto aDto = new AssignmentDto(eDto, pDto);
		return aDto;
	}

	private AssignmentDto convertToDtoWithoutEmployee(Assignment a) {
		AssignmentDto aDto = convertToDto(a);
		aDto.setEmployee(null);
		return aDto;
	}

	private Employee convertToDomainObject(EmployeeDto eDto) {
		List<Employee> allEmployees = service.getAllEmployees();
		for (Employee employee : allEmployees) {
			if (employee.getName().equals(eDto.getName())) {
				return employee;
			}
		}
		return null;
	}

	private List<ProjectDto> createAssignedProjectDtosForEmployee(Employee e) {
		List<Project> projectsForEmployee = service.getProjectsAssignedToEmployee(e);
		List<ProjectDto> projects = new ArrayList<>();
		for (Project project : projectsForEmployee) {
			projects.add(convertToDto(project));
		}
		return projects;
	}

	private List<AssignmentDto> createAssignmentDtosForEmployee(Employee e) {
		List<Assignment> assignmentsForEmployee = service.getAssignmentsForEmployee(e);
		List<AssignmentDto> assignments = new ArrayList<AssignmentDto>();
		for (Assignment a : assignmentsForEmployee) {
			assignments.add(convertToDtoWithoutEmployee(a));
		}
		return assignments;
	}
}