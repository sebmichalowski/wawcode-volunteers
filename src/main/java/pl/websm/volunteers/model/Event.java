package pl.websm.volunteers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("event")
public class Event implements Serializable {
    @Id
    private String id;
    private LocalDateTime localDateTime;
    private Position position;

    public Event(){

    }

    public Event(LocalDateTime localDateTime, Position position) {
        this.localDateTime = localDateTime;
        this.position = position;
    }
}
