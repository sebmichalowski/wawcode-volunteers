package pl.websm.volunteers.model;

import lombok.Data;
import org.springframework.data.geo.Distance;

import java.time.LocalDateTime;

@Data
public class EventCommand {
    private Event event;
    private Distance distance;
}
