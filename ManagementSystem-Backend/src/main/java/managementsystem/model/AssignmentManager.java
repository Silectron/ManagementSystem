package managementsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class AssignmentManager {
	private Set<Employee> employees;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employeess) {
		this.employees = employeess;
	}

	private Set<Assignment> assignments;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Assignment> getRegistrations() {
		return this.assignments;
	}

	public void setRegistrations(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	private Set<Project> projects;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Project> getEvents() {
		return this.projects;
	}

	public void setEvents(Set<Project> projects) {
		this.projects = projects;
	}

	private int id;

	public void setId(int value) {
		this.id = value;
	}

	@Id
	public int getId() {
		return this.id;
	}
}
