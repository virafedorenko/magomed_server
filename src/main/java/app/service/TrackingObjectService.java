package app.service;

import app.entity.TrackingObject;
import app.entity.User;

import java.util.List;

public interface TrackingObjectService {

    TrackingObject save(TrackingObject trackingObject);

    TrackingObject getById(String id);

    void delete(String id);

    List<TrackingObject> getByUser(User user);
}
