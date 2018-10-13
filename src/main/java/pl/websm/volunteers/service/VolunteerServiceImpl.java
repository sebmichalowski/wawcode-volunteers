package pl.websm.volunteers.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.repository.EventRepository;

import java.util.Set;

public class VolunteerServiceImpl implements VolunteerService {
    private final EventRepository eventRepository;

    public VolunteerServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Set<Event> findNearbyEvents(Point point, Distance distance) {
        return eventRepository.findByPositionPointNear(point, distance);
    }
}
