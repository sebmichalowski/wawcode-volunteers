package pl.websm.volunteers.model;

import lombok.Data;
import org.springframework.data.redis.core.index.GeoIndexed;

@Data
public class Position {
    @GeoIndexed
    private Double latitude;
    private Double longitude;
}
