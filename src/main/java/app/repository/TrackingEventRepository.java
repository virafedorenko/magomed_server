package app.repository;

import app.entity.TrackingEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingEventRepository extends CrudRepository<TrackingEvent, String> {
}
