package pl.websm.volunteers.model;

import org.springframework.data.geo.Distance;

public class OrganizationCommand {
    private String id;
    private String name;
    private Position position;
    private Distance distance;
}
