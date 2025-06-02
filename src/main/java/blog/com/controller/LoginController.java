package blog.com.controller;

import blog.com.Dto.DadosExibicaoAdmin;
import blog.com.Dto.DadosExibicaoUsuario;
import blog.com.dominio.Funcao;
import blog.com.dominio.Usuario;
import blog.com.infra.TokenService;
import blog.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String senha, Model model) {
        Usuario usuario = this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("email nao existe"));

        if (passwordEncoder.matches(senha, usuario.getSenha())) {
            System.out.println(email + " " + senha);
            String token = this.tokenService.generateToken(usuario); // token gerado para todos
            if (usuario.getRole().equals(Funcao.ADMIN.name())) {
                System.out.println(usuario.getRole());
                return ResponseEntity.ok(new DadosExibicaoAdmin(usuario.getNome(), token, usuario.getRole()));
            }
            // Usuários comuns recebem resposta sem o token (mas ele foi gerado)
            return ResponseEntity.ok(new DadosExibicaoUsuario(usuario.getNome(), usuario.getRole()));
        }
        model.addAttribute("erro", "Email ou senha inválidos");
        return ResponseEntity.badRequest().build();
    }
}