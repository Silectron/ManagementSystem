package managementsystem.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import managementsystem.model.Employee;
import managementsystem.model.Project;

@Repository
public class ProjectAssignmentRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public Employee createEmployee(String name) {
		Employee e = new Employee();
		e.setName(name);
		entityManager.persist(e);
		return e;
	}

	@Transactional
	public Employee getEmployee(String name) {
		Employee e = entityManager.find(Employee.class, name);
		return e;
	}

	@Transactional
	public Project createProject(String name, String status, Date startDate, Date endDate, String client) {
		Project p = new Project();
		p.setName(name);
		p.setStatus(status);
		p.setStartDate(startDate);
		p.setEndDate(endDate);
		p.setClient(client);
		entityManager.persist(p);
		return p;
	}

	@Transactional
	public Project getProject(String name) {
		Project p = entityManager.find(Project.class, name);
		return p;
	}

	@Transactional
	public List<Project> getProjectsBeforeADeadline(Date deadline) {
		TypedQuery<Project> q = entityManager.createQuery("select p from Project p where p.date < :deadline", Project.class);
		q.setParameter("deadline", deadline);
		List<Project> resultList = q.getResultList();
		return resultList;
	}

}