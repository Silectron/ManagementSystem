package managementsystem.dao;

import org.springframework.data.repository.CrudRepository;
import managementsystem.model.Lead;

public interface LeadRepository extends CrudRepository<Lead, String> {

	Lead findByName(String name);

}
