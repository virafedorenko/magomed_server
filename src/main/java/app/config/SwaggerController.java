package app.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SwaggerController {
    @RequestMapping("/swagger")
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }
}
