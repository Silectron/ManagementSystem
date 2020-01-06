package managementsystem.dto;

import java.sql.Date;
import java.sql.Time;

public class ProjectDto {

	private String name;
	private String status;
	private Date startDate;
	private Date endDate;

	public ProjectDto() {
	}

	public ProjectDto(String name) {
		this(name, "Acknowledged", Date.valueOf("1971-01-01"), Date.valueOf("1971-01-01"));
	}

	public ProjectDto(String name, String status, Date startDate, Date endDate) {
		this.name = name;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
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
}
