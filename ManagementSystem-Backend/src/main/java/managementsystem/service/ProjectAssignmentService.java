package managementsystem.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import managementsystem.dao.LeadRepository;
import managementsystem.model.Lead;
import managementsystem.dao.ProjectRepository;
import managementsystem.dao.EmployeeRepository;
import managementsystem.dao.AssignmentRepository;
import managementsystem.model.Project;
import managementsystem.model.Employee;
import managementsystem.model.Assignment;

@Service
public class ProjectAssignmentService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private AssignmentRepository assignmentRepository;


	@Transactional
	public Employee createEmployee(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Employee name cannot be empty!");
		} else if (employeeRepository.existsById(name)) {
			throw new IllegalArgumentException("Employee has already been created!");
		}
		Employee employee = new Employee();
		employee.setName(name);
		employeeRepository.save(employee);
		return employee;
	}

	@Transactional
	public Employee getEmployee(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Employee name cannot be empty!");
		}
		Employee employee = employeeRepository.findByName(name);
		return employee;
	}
	
	@Transactional
	public List<Employee> getAllEmployees() {
		return toList(employeeRepository.findAll());
	}
	
	@Transactional
	public Project buildProject(Project project, String name, String status, Date startDate, Date endDate, String client) {
		// Input validation
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "Project name cannot be empty! ";
		} else if (projectRepository.existsById(name)) {
			throw new IllegalArgumentException("Project has already been created!");
		}
		if (status == null) {
			error = error + "Project status cannot be empty! ";
		}
		if (startDate == null) {
			error = error + "Project start date cannot be empty! ";
		}
		if (endDate == null) {
			error = error + "Project end date cannot be empty! ";
		}
		if (endDate != null && startDate != null && endDate.before(startDate)) {
			error = error + "Project end time cannot be before project start time!";
		}
		if (client == null || client.trim().length() == 0) {
			error = error + "Project client cannot be empty! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		project.setName(name);
		project.setStatus(status);
		project.setStartDate(startDate);
		project.setEndDate(endDate);
		project.setClient(client);
		return project;
	}

	@Transactional
	public Project createProject(String name, String status, Date startDate, Date endDate, String client) {
		Project project = new Project();
		buildProject(project, name, status, startDate, endDate, client);
		projectRepository.save(project);
		return project;
	}

	@Transactional
	public Project getProject(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Project name cannot be empty!");
		}
		Project project = projectRepository.findByName(name);
		return project;
	}

	// This returns all objects of instance "Project" (Subclasses are filtered out)
	@Transactional
	public List<Project> getAllProjects() {
		return toList(projectRepository.findAll());
	}

	@Transactional
	public Assignment assign(Employee employee, Project project) {
		String error = "";
		if (employee == null) {
			error = error + "Employee needs to be selected for assignment! ";
		} else if (!employeeRepository.existsById(employee.getName())) {
			error = error + "Employee does not exist! ";
		}
		if (project == null) {
			error = error + "Project needs to be selected for assignment!";
		} else if (!projectRepository.existsById(project.getName())) {
			error = error + "Project does not exist!";
		}
		if (assignmentRepository.existsByEmployeeAndProject(employee, project)) {
			error = error + "Employee is already registered to this project!";
		}

		error = error.trim();

		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Assignment assignment = new Assignment();
		assignment.setId(employee.getName().hashCode() * project.getName().hashCode());
		assignment.setEmployee(employee);
		assignment.setProject(project);

		assignmentRepository.save(assignment);

		return assignment;
	}

	@Transactional
	public List<Assignment> getAllAssignments() {
		return toList(assignmentRepository.findAll());
	}

	@Transactional
	public Assignment getAssignmentByEmployeeAndProject(Employee employee, Project project) {
		if (employee == null || project == null) {
			throw new IllegalArgumentException("Employee or Project cannot be null!");
		}

		return assignmentRepository.findByEmployeeAndProject(employee, project);
	}
	@Transactional
	public List<Assignment> getAssignmentsForEmployee(Employee employee){
		if(employee == null) {
			throw new IllegalArgumentException("Employee cannot be null!");
		}
		return assignmentRepository.findByEmployee(employee);
	}

	@Transactional
	public List<Assignment> getAssignmentsByEmployee(Employee employee) {
		return toList(assignmentRepository.findByEmployee(employee));
	}

	@Transactional
	public List<Project> getProjectsAssignedToEmployee(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException("Employee cannot be null!");
		}
		List<Project> projectsAssignedToEmployee = new ArrayList<>();
		for (Assignment a : assignmentRepository.findByEmployee(employee)) {
			projectsAssignedToEmployee.add(a.getProject());
		}
		return projectsAssignedToEmployee;
	}
	
	@Transactional
	public Lead createLead(String name) {
		if(name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Lead name cannot be empty!");
		} else if(leadRepository.existsById(name)) {
			throw new IllegalArgumentException("Lead has already been created!");
		}
		
		Lead l = new Lead();
		l.setName(name);
		leadRepository.save(l);
		
		return l;
	}
	
	@Transactional
	public Lead getLead(String name) {
		if(name == null || name == "" || name == " ") {
			throw new IllegalArgumentException("Employee name cannot be empty!");
		}
		
		for(Lead p : leadRepository.findAll()) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("A lead with the specified name does not exist.");
	
	}
	
	@Transactional
	public void leadsProject(Lead l, Project p) {
		boolean leadExists = false;
		boolean projectExists = false;
		if(p == null) {
			throw new IllegalArgumentException("Project must be specified.");
		}
		for(Lead lea : leadRepository.findAll()) {
			if(lea.getName().equals(l.getName())) {
				leadExists = true;
			}
		}
		for(Project pro : projectRepository.findAll()) {
			if(pro.getName().equals(l.getName())) {
				projectExists = true;
			}
		}
		
		if(!leadExists) {
			throw new IllegalArgumentException("Lead needs to be selected for projects!");
		}
		if(!projectExists) {
			throw new IllegalArgumentException("Project does not exist!");
		}
		
		List<Project> projectsLeaded = new ArrayList<>();
		if(l.getProjects() != null) {
			projectsLeaded = l.getProjects();
		}
		projectsLeaded.add(p);
		
		l.setProjects(projectsLeaded);	
		leadRepository.save(l);
	}
	
	@Transactional
	public List<Lead> getAllLeads() {
		return toList(leadRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}