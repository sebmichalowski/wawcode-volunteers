package pl.websm.volunteers.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.test.context.junit4.SpringRunner;
import pl.websm.volunteers.model.Event;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest

public class CustomRepositoryImplTest {
    @Autowired
    CustomRepository<Event, String> customRepository;

    @Test
    public void findByPositionPointNearWithDistance() {
        //when
        Point point = new Point(13D, 38D);
        Distance distance = new Distance(900000, RedisGeoCommands.DistanceUnit.KILOMETERS);
        customRepository.findByPositionPointNearWithDistance(point, distance);
    }
}