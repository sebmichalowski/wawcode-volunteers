package pl.websm.volunteers.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import pl.websm.volunteers.model.AreaOfExpertise;
import pl.websm.volunteers.model.Organization;
import pl.websm.volunteers.model.Volunteer;
import pl.websm.volunteers.repository.OrganizationRepository;
import pl.websm.volunteers.repository.VolunteerRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OrganizationServiceImpl implements OrganizationService {
    private final VolunteerRepository volunteerRepository;
    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(VolunteerRepository volunteerRepository, OrganizationRepository organizationRepository) {
        this.volunteerRepository = volunteerRepository;
        this.organizationRepository = organizationRepository;
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

    private Set<Volunteer> findVolunteerWithinGivenRadius(Point point, Distance distance) {
        return volunteerRepository.findByPositionPointNear(point, distance);
    }
}
