package pl.websm.volunteers.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.websm.volunteers.model.Event;

import java.util.Set;

@Repository
public interface EventRepository extends CrudRepository<Event, String>, CustomRepository<Event, String> {
    Set<Event> findByPositionPointNear(Point point, Distance distance);
}
