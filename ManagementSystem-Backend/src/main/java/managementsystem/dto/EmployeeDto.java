package managementsystem.dto;

import java.util.Collections;
import java.util.List;

public class EmployeeDto {

	private String name;
	private List<ProjectDto> projectsAssigned;
	public EmployeeDto() {
	}

	@SuppressWarnings("unchecked")
	public EmployeeDto(String name) {
		this(name, Collections.EMPTY_LIST);
	}

	public EmployeeDto(String name, List<ProjectDto> projects) {
		this.name = name;
		this.projectsAssigned = projects;
	}

	public String getName() {
		return name;
	}

	public List<ProjectDto> getProjectsAssigned() {
		return projectsAssigned;
	}

	public void setProjectsAssigned(List<ProjectDto> projects) {
		this.projectsAssigned = projects;
	}
}