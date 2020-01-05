package managementsystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import managementsystem.model.Project;
import managementsystem.model.Employee;
import managementsystem.model.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

	List<Assignment> findByEmployee(Employee employeeName);

	boolean existsByEmployeeAndProject(Employee employee, Project projectName);

	Assignment findByEmployeeAndProject(Employee employee, Project projectName);

}