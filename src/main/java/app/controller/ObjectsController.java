package app.controller;

import app.entity.TrackingObject;
import app.exception.ResourceNotFoundException;
import app.service.TrackingObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ObjectsController {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectsController.class);
    @Autowired
    private TrackingObjectService trackingObjectService;

    @GetMapping("/trackingObject")
    public TrackingObject getTrackingObject(@RequestParam(name = "id") String id) {
        TrackingObject object = trackingObjectService.getById(id);
        if (object == null) {
            throw new ResourceNotFoundException("Object", "id", id);
        }
        return object;
    }

    @PostMapping("/saveTrackingObject")
    public TrackingObject saveTrackingObject(@RequestBody TrackingObject trackingObject) {
        return trackingObjectService.save(trackingObject);
    }

    @DeleteMapping("/deleteTrackingObject")
    public void deleteTrackingObject(@RequestParam(name = "id") String id) {
        trackingObjectService.delete(id);
    }
}
