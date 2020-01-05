package managementsystem.dao;

import org.springframework.data.repository.CrudRepository;

import managementsystem.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	Employee findByName(String name);
}
