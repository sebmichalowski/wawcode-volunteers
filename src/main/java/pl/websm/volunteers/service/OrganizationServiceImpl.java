package pl.websm.volunteers.service;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import pl.websm.volunteers.model.AreaOfExpertise;
import pl.websm.volunteers.model.Volunteer;
import pl.websm.volunteers.repository.VolunteerRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class OrganizationServiceImpl implements OrganizationService{
    private final VolunteerRepository volunteerRepository;

    public OrganizationServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Set<Volunteer> findVolunteerByAreaOfExpertise(Point point, AreaOfExpertise areaOFExpertise) {
        Set<Volunteer> volunteers;
        Integer tempDistance = 10;
        do {
            volunteers = findVolunteerWithinGivenRadius(point, new Distance(tempDistance));
            tempDistance*= 2;
        } while (volunteers.size() < 6 && tempDistance < 50);

        return volunteers.stream()
                .filter(volunteer -> volunteer.getAreaOfExpertise() == areaOFExpertise).collect(Collectors.toSet());
    }

    private Set<Volunteer> findVolunteerWithinGivenRadius(Point point, Distance distance){
        return volunteerRepository.findByPositionPointNear(point, distance);
    }
}
