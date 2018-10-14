package pl.websm.volunteers.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringRunner;
import pl.websm.volunteers.model.AreaOfExpertise;
import pl.websm.volunteers.model.Position;
import pl.websm.volunteers.model.Volunteer;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class VolunteerRepositoryTest {
    @Autowired
    VolunteerRepository volunteerRepository;

    @Test
    public void findByPositionPointNear() {
        Volunteer volunteer = new Volunteer();
        volunteer.setAreaOfExpertise(AreaOfExpertise.HARDWARE);
        volunteer.setPosition(new Position(new Point(14D, 37D)));
        Volunteer volunteer1 = volunteerRepository.save(volunteer);
        System.out.println(volunteer1.getId());
    }
}