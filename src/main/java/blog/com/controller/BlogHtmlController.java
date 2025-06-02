package blog.com.controller;


import blog.com.dominio.Posts;
import blog.com.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogHtmlController {
    @Autowired
    private PostService postService;
    @GetMapping("/home")
    public String tela(Model model) {
        Posts postsDestaque = postService.buscarPostEmDestaque();
        List<Posts> recentes = postService.listarPostsRecentes();
        model.addAttribute("postsDestaque", postsDestaque);
        model.addAttribute("recentes", recentes);
       return "blog"; // Template HTML: src/main/resources/templates/Admin.html
    }
}
