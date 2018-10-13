package pl.websm.volunteers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@RedisHash("event")
public class Event {
    @Id
    private String id;
    private LocalDateTime localDateTime;
    private Position position;
}
