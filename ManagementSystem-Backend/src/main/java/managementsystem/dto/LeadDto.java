package managementsystem.dto;

import java.util.List;

public class LeadDto extends EmployeeDto {

	private List<ProjectDto> projects;

	public LeadDto() {
	}

	public LeadDto(String name) {
		super(name);
	}

	public LeadDto(String name, List<ProjectDto> projects) {
		super(name);
		this.projects = projects;
	}

	public void setLeads(List<ProjectDto> projects) {
		this.projects = projects;
	}

	public List<ProjectDto> getProjectsLeaded() {
		return projects;
	}

}
