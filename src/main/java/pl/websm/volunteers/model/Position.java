package pl.websm.volunteers.model;

import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.index.GeoIndexed;

import java.io.Serializable;


@Data
public class Position implements Serializable {
    @GeoIndexed
    private Point point;

    public Position(){

    }
    public Position(Double latitude, Double longitude) {
        point = new Point(latitude, longitude);
    }
}
