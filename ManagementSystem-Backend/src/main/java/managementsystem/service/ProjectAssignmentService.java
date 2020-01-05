package managementsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public Lead createLead(String name) {
		if(name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Lead name cannot be empty!");
		} else if(leadRepository.existsById(name)) {
			throw new IllegalArgumentException("Lead has already been created!");
		}
		
		Lead p = new Lead();
		p.setName(name);
		leadRepository.save(p);
		
		return p;
	}
	
	@Transactional
	public Lead getLead(String name) {
		if(name == null || name == "" || name == " ") {
			throw new IllegalArgumentException("Person name cannot be empty!");
		}
		
		for(Lead p : leadRepository.findAll()) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("A lead with the specified name does not exist.");
	
	}

}