package pl.websm.volunteers.repository;

import org.springframework.data.repository.CrudRepository;
import pl.websm.volunteers.model.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, String> {
}
