package app.repository;

import app.entity.TrackingObject;
import app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingObjectRepository extends CrudRepository<TrackingObject, String> {

    List<TrackingObject> findByUser(@Param("user") User user);
}
