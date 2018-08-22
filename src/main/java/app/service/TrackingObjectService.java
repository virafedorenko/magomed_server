package app.service;

import app.entity.TrackingObject;

public interface TrackingObjectService {

    TrackingObject save(TrackingObject trackingObject);

    TrackingObject getById(String id);

    void delete(String id);
}
