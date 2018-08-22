package app.repository;

import app.entity.TrackingObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingObjectRepository extends CrudRepository<TrackingObject, String> {
}
