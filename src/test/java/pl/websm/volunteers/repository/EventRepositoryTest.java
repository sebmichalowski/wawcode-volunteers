package pl.websm.volunteers.repository;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.test.context.junit4.SpringRunner;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.model.Organization;
import pl.websm.volunteers.model.Position;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    private Event event;
    private Event event1;
    private Event event2;

    @Before
    public void setUp() throws Exception {
    }

//    @Test
//    public void save_get(){
//        eventRepository.save(event);
//        eventRepository.save(event1);
//        eventRepository.save(event2);
//    }

    @Test
    public void find_radius() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Position position = new Position(13.171389338970184, 38.115556395496299);
        Position position1 = new Position(13.131389338970184, 38.155556395496299);
        Position position2 = new Position(13.391389338970184, 38.145556395496299);
        Organization organization = new Organization();
        organization.setName("Some org");
        organization.setPosition(position);


        Organization organization1 = organizationRepository.save(organization);
        event = new Event(localDateTime, position, organization1.getId());
        event1 = new Event(localDateTime, position1, organization1.getId());
        event2 = new Event(localDateTime, position2, organization1.getId());
        eventRepository.save(event);
        eventRepository.save(event1);
        eventRepository.save(event2);

        //when
        Point point = new Point(13D, 38D);
        Distance distance = new Distance(900000, RedisGeoCommands.DistanceUnit.KILOMETERS);
        Set<Event> eventsInRange = eventRepository.findByPositionPointNear(point, distance);

        //Then
        assertEquals(3, eventsInRange.size());
    }
}