package app.controller;

import app.auth.UserPrincipal;
import app.entity.TrackingObject;
import app.entity.User;
import app.exception.ResourceNotFoundException;
import app.service.TrackingObjectService;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ObjectsController {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectsController.class);
    @Autowired
    private TrackingObjectService trackingObjectService;
    @Autowired
    private UserService userService;

    @GetMapping("/trackingObject")
    public TrackingObject getTrackingObject(@RequestParam(name = "id") String id) {
        TrackingObject object = trackingObjectService.getById(id);
        if (object == null) {
            throw new ResourceNotFoundException("Object", "id", id);
        }
        User user = object.getUser();
        String authenticatedUserId = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (!user.getId().equals(authenticatedUserId)) {
            throw new AccessDeniedException("You cannot access not yours objects!");
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

    @GetMapping("/getObjectsByUser")
    public List<TrackingObject> getObjectsByUser(@RequestParam(name = "id") String id) {
        User user = userService.findUserById(id);
        return trackingObjectService.getByUser(user);
    }
}
