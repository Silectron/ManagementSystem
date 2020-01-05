package managementsystem.dto;

import java.sql.Date;
import java.sql.Time;

public class ProjectDto {

	private String name;
	private Date date;
	private Time startTime;
	private Time endTime;

	public ProjectDto() {
	}

	public ProjectDto(String name) {
		this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	}

	public ProjectDto(String name, Date date, Time startTime, Time endTime) {
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}
}
