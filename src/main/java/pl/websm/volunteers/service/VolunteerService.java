package pl.websm.volunteers.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.model.Volunteer;

import java.util.Optional;
import java.util.Set;

public interface VolunteerService {
    Set<Event> findNearbyEvents(Point point, Distance distance);

    Optional<Volunteer> getVolunteer(String id);
}
