package app.service;

import app.entity.TrackingObject;

import java.util.List;

public interface TrackingObjectService {

    TrackingObject save(TrackingObject trackingObject);

    TrackingObject getById(String id);

    void delete(String id);

    List<TrackingObject> getByUser(String userId);
}
