package pl.websm.volunteers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("organization")
public class Organization implements Serializable {
    @Id
    private String id;
    private String name;
    private Position position;
}
