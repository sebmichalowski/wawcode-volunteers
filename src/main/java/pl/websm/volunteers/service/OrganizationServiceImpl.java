package pl.websm.volunteers.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import pl.websm.volunteers.model.AreaOfExpertise;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.model.Organization;
import pl.websm.volunteers.model.Volunteer;
import pl.websm.volunteers.repository.EventRepository;
import pl.websm.volunteers.repository.OrganizationRepository;
import pl.websm.volunteers.repository.VolunteerRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final VolunteerRepository volunteerRepository;
    private final OrganizationRepository organizationRepository;
    private final EventRepository eventRepository;

    public OrganizationServiceImpl(VolunteerRepository volunteerRepository, OrganizationRepository organizationRepository, EventRepository eventRepository) {
        this.volunteerRepository = volunteerRepository;
        this.organizationRepository = organizationRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Set<Volunteer> findVolunteerByAreaOfExpertise(Point point, AreaOfExpertise areaOFExpertise) {
        Set<Volunteer> volunteers;
        Integer tempDistance = 10;
        do {
            volunteers = findVolunteerWithinGivenRadius(point, new Distance(tempDistance));
            tempDistance *= 2;
        } while (volunteers.size() < 6 && tempDistance < 50);

        return volunteers.stream()
                .filter(volunteer -> volunteer.getAreaOfExpertise() == areaOFExpertise).collect(Collectors.toSet());
    }

    @Override
    public Optional<Organization> getOrganization(String id) {
        return organizationRepository.findById(id);
    }

    @Override
    public Optional<Event> addEvent(Event event) {
        Event addedEvent = eventRepository.save(event);
        if (addedEvent.getId().isEmpty()){
            return Optional.empty();
        }
        return Optional.of(addedEvent);
    }

    private Set<Volunteer> findVolunteerWithinGivenRadius(Point point, Distance distance) {
        return volunteerRepository.findByPositionPointNear(point, distance);
    }
}
