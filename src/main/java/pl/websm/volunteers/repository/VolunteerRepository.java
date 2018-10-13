package pl.websm.volunteers.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import pl.websm.volunteers.model.Volunteer;

import java.util.Set;

public interface VolunteerRepository extends CrudRepository<Volunteer, String> {
    Set<Volunteer> findByPositionPointNear(Point point, Distance distance);

}
