package managementsystem.dao;

import java.sql.Date;
import java.sql.Time;
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
		Employee p = new Employee();
		p.setName(name);
		entityManager.persist(p);
		return p;
	}

	@Transactional
	public Employee getEmployee(String name) {
		Employee p = entityManager.find(Employee.class, name);
		return p;
	}

	@Transactional
	public Project createProject(String name, Date date, Time startTime, Time endTime) {
		Project e = new Project();
		e.setName(name);
		e.setDate(date);
		e.setStartTime(startTime);
		e.setEndTime(endTime);
		entityManager.persist(e);
		return e;
	}

	@Transactional
	public Project getProject(String name) {
		Project e = entityManager.find(Project.class, name);
		return e;
	}

	@Transactional
	public List<Project> getProjectsBeforeADeadline(Date deadline) {
		TypedQuery<Project> q = entityManager.createQuery("select e from Project e where e.date < :deadline", Project.class);
		q.setParameter("deadline", deadline);
		List<Project> resultList = q.getResultList();
		return resultList;
	}

}