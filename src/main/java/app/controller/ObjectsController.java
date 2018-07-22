package app.controller;

import org.omg.CORBA.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@RestController
public class ObjectsController {

    private static final Logger LOG = LoggerFactory.getLogger(Object.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public void test(HttpServletResponse response) throws SQLException, IOException {
        LOG.info(dataSource.getConnection().toString());
        String sql = "select count(*) from objects";
        int count=jdbcTemplate.queryForObject(sql, Integer.class);
        response.getWriter().write("Count: "+count);

    }
}
