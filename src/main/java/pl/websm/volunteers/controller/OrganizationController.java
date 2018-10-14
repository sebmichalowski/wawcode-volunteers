package pl.websm.volunteers.controller;

import org.springframework.data.geo.Distance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.websm.volunteers.model.AreaOfExpertise;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.model.Organization;
import pl.websm.volunteers.model.Volunteer;
import pl.websm.volunteers.service.OrganizationService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @RequestMapping(
            value = "api/{organizationId}/{areaOfExpertise}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findVolunteerWithSpecificSkill(@PathVariable String organizationId,
                                                                 @PathVariable AreaOfExpertise areaOfExpertise) {
        Optional<Organization> organization = organizationService.getOrganization(organizationId);
        if (organization.isPresent()){
            organizationService.findVolunteerByAreaOfExpertise(organization.get().getPosition().getPoint(),
                                                                areaOfExpertise);
            return new ResponseEntity<>("User will get notified shortly", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }
}
