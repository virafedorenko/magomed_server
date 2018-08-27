package app.controller;

import app.entity.TrackingEvent;
import app.entity.TrackingObject;
import app.exception.ResourceNotFoundException;
import app.service.TrackingEventService;
import app.service.TrackingObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventsController {
    private static final Logger LOG = LoggerFactory.getLogger(ObjectsController.class);
    @Autowired
    private TrackingEventService trackingEventService;
    @Autowired
    private TrackingObjectService trackingObjectService;

    @GetMapping("/trackingEvent")
    public TrackingEvent getTrackingObject(@RequestParam(name = "id") String id) {
        TrackingEvent event = trackingEventService.getById(id);
        if (event == null) {
            throw new ResourceNotFoundException("Object", "id", id);
        }
        return event;
    }

    @PostMapping("/saveTrackingEvent")
    public TrackingEvent saveTrackingObject(@RequestBody TrackingEvent trackingEvent) {
        return trackingEventService.save(trackingEvent);
    }

    @DeleteMapping("/deleteTrackingEvent")
    public void deleteTrackingObject(@RequestParam(name = "id") String id) {
        trackingEventService.delete(id);
    }

    @GetMapping("/getEventsByObject")
    public List<TrackingEvent> getEventsByObject(@RequestParam(name = "id") String id) {
        TrackingObject object = trackingObjectService.getById(id);
        return trackingEventService.getByObject(object);
    }
}
