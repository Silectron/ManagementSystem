package managementsystem.dto;

import java.sql.Date;

public class ProjectDto {

	private String name;
	private String status;
	private Date startDate;
	private Date endDate;
	private String client;

	public ProjectDto() {
	}

	public ProjectDto(String name, String client) {
		this(name, "Acknowledged", Date.valueOf("1971-01-01"), Date.valueOf("1971-01-01"), client);
	}

	public ProjectDto(String name, String status, Date startDate, Date endDate, String client) {
		this.name = name;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
	
	public String getClient() {
		return this.client;
	}
}
