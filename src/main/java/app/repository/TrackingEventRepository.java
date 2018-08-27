package app.repository;

import app.entity.TrackingEvent;
import app.entity.TrackingObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingEventRepository extends CrudRepository<TrackingEvent, String> {
    List<TrackingEvent> findByObject(@Param("object") TrackingObject object);
}
