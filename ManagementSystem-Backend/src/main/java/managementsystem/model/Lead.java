package managementsystem.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.List;
import javax.persistence.ManyToMany;

@Entity
public class Lead extends Employee {

	private List<Project> projects;

	@ManyToMany(fetch = FetchType.EAGER)
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}
