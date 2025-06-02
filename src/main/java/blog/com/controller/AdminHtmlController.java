package blog.com.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class AdminHtmlController {

    @GetMapping("/telaAdmin")
    public String tela(Model model) {
        return "Admin"; // Template HTML: src/main/resources/templates/Admin.html
    }
}
