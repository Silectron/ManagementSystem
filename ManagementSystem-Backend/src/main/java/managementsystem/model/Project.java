package managementsystem.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "project")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "TYPE")
public class Project {
	private String name;

	public void setName(String value) {
		this.name = value;
	}

	@Id
	public String getName() {
		return this.name;
	}

	private String status;

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	private Date startDate;

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	private Date endDate;

	public void setEndDate(Date date) {
		this.endDate = date;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	private List<Lead> leads;

	@ManyToMany(fetch = FetchType.EAGER)
	public List<Lead> getLeads() {
		return leads;
	}

	public void setLeads(List<Lead> leads) {
		this.leads = leads;
	}
	
	private String client;

	public void setClient(String clientName) {
		this.client = clientName;
	}

	public String getClient() {
		return this.client;
	}

}
