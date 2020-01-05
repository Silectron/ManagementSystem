package managementsystem.dto;

import managementsystem.dto.ProjectDto;
import managementsystem.dto.EmployeeDto;

public class AssignmentDto {

	private EmployeeDto employee;
	private ProjectDto project;

	public AssignmentDto() {
	}

	public AssignmentDto(EmployeeDto employee, ProjectDto project) {
		this.employee = employee;
		this.project = project;
	}

	public ProjectDto getProject() {
		return project;
	}

	public void setProject(ProjectDto project) {
		this.project = project;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}
}