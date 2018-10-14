package pl.websm.volunteers.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
@Data
@RedisHash("organization")
public class Organization {
    @Id
    private String id;
    private String name;
    private Position position;
}
