package pl.websm.volunteers.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.test.context.junit4.SpringRunner;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.model.Position;


import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;
import java.util.Set;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {
    @Autowired
    EventRepository eventRepository;

    private Event event;
    private Event event1;
    private Event event2;
    @Before
    public void setUp() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
        Position position = new Position(13.361389338970184, 38.115556395496299);
        Position position1 = new Position(13.361389338970184, 38.115556395496299);
        Position position2 = new Position(13.361389338970184, 38.115556395496299);
        event = new Event(localDateTime, position);
        event1 = new Event(localDateTime, position1);
        event2 = new Event(localDateTime, position2);
        eventRepository.save(event);
        eventRepository.save(event1);
        eventRepository.save(event2);
    }

    @Test
    public void save_get(){
        eventRepository.save(event);
        eventRepository.save(event1);
        eventRepository.save(event2);
    }

    @Test
    public void find_radius(){
        //when
        Point point = new Point(13D, 38D);
        Distance distance = new Distance(900000, RedisGeoCommands.DistanceUnit.KILOMETERS);
        Set<Event> eventsInRange = eventRepository.findByPositionPointNear(point, distance);

        //Then
        assertEquals(3, eventsInRange.size());
    }
}