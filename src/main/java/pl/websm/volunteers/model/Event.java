package pl.websm.volunteers.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@RedisHash("event")
public class Event {
    @Id
    private String id;
    private LocalDateTime localDateTime;
    private Position position;

    public Event() {

    }

    public Event(LocalDateTime localDateTime, Position position) {
        this.localDateTime = localDateTime;
        this.position = position;
    }
}
