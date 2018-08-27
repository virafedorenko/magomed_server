package app.repository;

import app.entity.TrackingObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingObjectRepository extends CrudRepository<TrackingObject, String> {
    @Query("select s from Object s where s.user.id=:id")
    List<TrackingObject> getByUser(@Param("id") String id);
}
