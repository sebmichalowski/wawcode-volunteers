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

import java.time.LocalDateTime;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class VolunteerRepositoryTest {
    @Autowired
    VolunteerRepository volunteerRepository;

    @Test
    public void findByPositionPointNear() {
        Volunteer volunteer = new Volunteer();
        volunteer.setPosition(new Position(new Point(13.171389338970185D, 38.1155563954963)));
        volunteer.setAreaOfExpertise(AreaOfExpertise.HARDWARE);
        volunteer.setAvailabilityFrom(LocalDateTime.now().toLocalTime());
        volunteer.setAvailabilityUntil(LocalDateTime.now().toLocalTime());
        Volunteer volunteer1 = volunteerRepository.save(volunteer);
        System.out.println(volunteer1.getId());
    }
}