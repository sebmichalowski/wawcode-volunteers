package pl.websm.volunteers.service;

import org.springframework.data.geo.Point;
import pl.websm.volunteers.model.AreaOfExpertise;
import pl.websm.volunteers.model.Organization;
import pl.websm.volunteers.model.Volunteer;

import java.util.Optional;
import java.util.Set;

public interface OrganizationService {
    Set<Volunteer> findVolunteerByAreaOfExpertise(Point point, AreaOfExpertise areaOFExpertise);
    Optional<Organization> getOrganization(String id);
}
