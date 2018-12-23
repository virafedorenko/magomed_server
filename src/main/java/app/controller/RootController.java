package app.controller;

import app.entity.Root;
import app.exception.ResourceNotFoundException;
import app.service.RootService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class RootController {

    private static final Logger LOG = LoggerFactory.getLogger(RootController.class);
    @Autowired
    private RootService rootService;

    @GetMapping("/root")
    public Root getRoot(@RequestParam(name = "id") String id) {
        Root root= rootService.getById(id);
        if (root== null) {
            throw new ResourceNotFoundException("Root", "id", id);
        }
        return  root;
    }

    @PostMapping("/saveRoot")
    public Root saveRoot(@RequestBody Root root) {
        return rootService.save(root);
    }

    @DeleteMapping("/deleteRoot")
    public void deleteRoot(@RequestParam(name = "id") String id) {
        rootService.delete(id);
    }
}
