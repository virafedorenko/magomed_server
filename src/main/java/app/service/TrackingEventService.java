package app.service;

import app.entity.TrackingEvent;

import java.util.List;

public interface TrackingEventService {

    TrackingEvent save(TrackingEvent trackingObject);

    TrackingEvent getById(String id);

    void delete(String id);

    List<TrackingEvent> getByObject(String objectId);
}
