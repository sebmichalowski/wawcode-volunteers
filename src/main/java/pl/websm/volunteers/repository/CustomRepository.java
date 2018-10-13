package pl.websm.volunteers.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import pl.websm.volunteers.model.EventCommand;

import java.util.Set;

interface CustomRepository<T, T1> {
    Set<EventCommand> findByPositionPointNearWithDistance(Point point, Distance distance);
}
