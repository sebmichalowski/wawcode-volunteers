package pl.websm.volunteers.controller;

import org.springframework.data.geo.Distance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.model.Volunteer;
import pl.websm.volunteers.service.VolunteerService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @RequestMapping(
            value = "api/{userId}/{distanceInKm}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Event>> getListOfEventsNearby(@PathVariable String userId,
                                                            @PathVariable Integer distanceInKm) {
        Optional<Volunteer> volunteer = volunteerService.getVolunteer(userId);
        if (volunteer.isPresent()) {
            Set<Event> nearbyEvents = volunteerService.findNearbyEvents(volunteer.get().getPosition().getPoint(),
                    new Distance(distanceInKm));
            return new ResponseEntity<>(nearbyEvents, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HashSet<>(), HttpStatus.BAD_REQUEST);
        }
    }
}

