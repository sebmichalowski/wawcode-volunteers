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
    private String organization_id;

    public Event() {

    }

    public Event(LocalDateTime localDateTime, Position position, String organization_id) {
        this.localDateTime = localDateTime;
        this.position = position;
        this.organization_id = organization_id;
    }
}
