package managementsystem.dao;

import org.springframework.data.repository.CrudRepository;

import managementsystem.model.Project;

public interface ProjectRepository extends CrudRepository<Project, String> {

	Project findByName(String name);
	
	Project findByStatus(String status);
}