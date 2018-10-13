package pl.websm.volunteers.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import pl.websm.volunteers.model.Event;
import pl.websm.volunteers.model.EventCommand;

import java.util.*;

public class CustomRepositoryImpl implements CustomRepository<String, Object> {
    private final RedisTemplate<String, Object> redisTemplate;


    public CustomRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Set<EventCommand> findByPositionPointNearWithDistance(Point point, Distance distance) {
        Set<EventCommand> eventCommands = new HashSet<>();
        try {
            Object execute = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates();
                    return connection.geoCommands().geoRadius("event:position:point".getBytes(), new Circle(point, distance), geoRadiusCommandArgs);
                }
            });
            GeoResults geoResults = (GeoResults) execute;
            Iterator iterator = geoResults.iterator();

            while (iterator.hasNext()) {
                GeoResult geoResult = (GeoResult) iterator.next();

                EventCommand eventCommand = new EventCommand();
                eventCommand.setDistance(geoResult.getDistance());

                RedisGeoCommands.GeoLocation content = (RedisGeoCommands.GeoLocation) geoResult.getContent();
                System.out.println(content.getName().getClass());
                eventCommand.setEvent((Event) content.getName());
                eventCommands.add(eventCommand);
            }
            return eventCommands;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventCommands;
    }
}
