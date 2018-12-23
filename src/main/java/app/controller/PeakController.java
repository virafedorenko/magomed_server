package app.controller;

import app.auth.UserPrincipal;
import app.auth.jwt.JwtTokenProvider;
import app.entity.Peak;
import app.entity.Root;
import app.entity.User;
import app.exception.ResourceNotFoundException;
import app.service.PeakService;
import app.service.RootService;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PeakController {

    private static final Logger LOG = LoggerFactory.getLogger(PeakController.class);
    @Autowired
    private PeakService peakService;
    @Autowired
    private RootService rootService;

    @GetMapping("/peak")
    public Peak getPeak(@RequestParam(name = "id") String id) {
        Peak peak = peakService.getById(id);
        if (peak == null) {
            throw new ResourceNotFoundException("Peak", "id", id);
        }
        return peak;
    }


    @PostMapping("/savePeak")
    public Peak savePeak(@RequestBody Peak peak) {
        return peakService.save(peak);
    }

    @DeleteMapping("/deletePeak")
    public void deletePeak(@RequestParam(name = "id") String id) {
        peakService.delete(id);
    }

    @GetMapping("/getPeakByRoot")
    public List<Peak> getPeaksByRoot(@RequestParam(name = "id") String id) {
        Root root = rootService.getById(id);
        return peakService.findByRoot(root);
    }

    @GetMapping("/peaks")
    public List<Peak> getAllPeaks(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        return peakService.findAll();
    }
}
