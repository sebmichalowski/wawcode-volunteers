package pl.websm.volunteers.service;

import org.springframework.data.geo.Point;
import pl.websm.volunteers.model.AreaOfExpertise;
import pl.websm.volunteers.model.Volunteer;

import java.util.Set;

public interface OrganizationService {
    Set<Volunteer> findVolunteerByAreaOfExpertise(Point point, AreaOfExpertise areaOFExpertise);
}
