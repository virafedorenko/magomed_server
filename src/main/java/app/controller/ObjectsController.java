package app.controller;

import app.entity.TrackingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@RestController
public class ObjectsController {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectsController.class);

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public void test(HttpServletResponse response) throws SQLException, IOException {
        LOG.info(dataSource.getConnection().toString());
        String sql = "select count(*) from objects";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        response.getWriter().write("Count: " + count);
    }

    @GetMapping("/trackingObject")
    public TrackingObject getTrackingObject(@RequestParam(name = "id") String id) {
        return null;
    }

    @PostMapping("/saveTrackingObject")
    public TrackingObject saveTrackingObject(@RequestBody TrackingObject trackingObject) {
        return null;
    }

    @DeleteMapping("/deleteTrackingObject")
    public TrackingObject deleteTrackingObject(@RequestParam(name = "id") String id) {
        return null;
    }
}
