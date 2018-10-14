package pl.websm.volunteers.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.model.Volunteer;
import pl.websm.volunteers.repository.EventRepository;
import pl.websm.volunteers.repository.VolunteerRepository;

import java.util.Optional;
import java.util.Set;

public class VolunteerServiceImpl implements VolunteerService {
    private final EventRepository eventRepository;
    private final VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(EventRepository eventRepository, VolunteerRepository volunteerRepository) {
        this.eventRepository = eventRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Set<Event> findNearbyEvents(Point point, Distance distance) {
        return eventRepository.findByPositionPointNear(point, distance);
    }

    public Optional<Volunteer> getVolunteer(String id) {
        return volunteerRepository.findById(id);
    }
}
