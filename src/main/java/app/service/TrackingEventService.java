package app.service;

import app.entity.TrackingEvent;

public interface TrackingEventService {

    TrackingEvent save(TrackingEvent trackingObject);

    TrackingEvent getById(String id);

    void delete(String id);
}
