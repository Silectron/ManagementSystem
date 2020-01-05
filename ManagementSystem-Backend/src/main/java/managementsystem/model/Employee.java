package managementsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	private String name;

	public void setName(String value) {
		this.name = value;
	}

	@Id
	public String getName() {
		return this.name;
	}
}
