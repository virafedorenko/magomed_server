package app.repository;

import app.entity.TrackingEvent;
import app.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingEventRepository extends CrudRepository<TrackingEvent, String> {
    @Query("select s from Event s where s.object.id=:id")
    List<TrackingEvent> getByObject(@Param("id") String id);
}
