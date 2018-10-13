package pl.websm.volunteers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("organization")
public class Organization {
    @Id
    private String id;
    private String name;
    private Position position;
}
