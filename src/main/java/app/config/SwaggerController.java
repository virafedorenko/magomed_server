package app.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
public class SwaggerController {
/*
    @RequestMapping(method = RequestMethod.GET)
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }*/
    @RequestMapping(value= "/swagger-ui.html/**", method=RequestMethod.GET)
    public void corsHeaders(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.addHeader("Access-Control-Max-Age", "3600");
    }
}
