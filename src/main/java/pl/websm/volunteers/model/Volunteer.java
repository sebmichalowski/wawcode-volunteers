package pl.websm.volunteers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalTime;

@RedisHash("volunteer")
public class Volunteer implements Serializable {
    @Id
    private String id;
    private String name;
    private String surname;
    private LocalTime availabilityFrom;
    private LocalTime availabilityUntil;
    private Position position;
}
