package pl.websm.volunteers.model;

import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.index.GeoIndexed;


@Data
public class Position {
    @GeoIndexed
    private Point point;

    public Position(){

    }
    public Position(Double latitude, Double longitude) {
        point = new Point(latitude, longitude);
    }
}
