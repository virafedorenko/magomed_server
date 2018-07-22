package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class ObjectsController {

    @Autowired
    DataSource dataSource;

    @RequestMapping("/")
    public void test() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
