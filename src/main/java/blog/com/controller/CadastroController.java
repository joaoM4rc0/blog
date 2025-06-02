package blog.com.controller;

import blog.com.Dto.DadosCadastroUsuario;
import blog.com.dominio.Funcao;
import blog.com.dominio.Usuario;
import blog.com.infra.TokenService;
import blog.com.repository.UserRepository;
import blog.com.Dto.DadosDetalhamentoUsuario;
import blog.com.Dto.ValidacaoDadosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping("/auth")
public class CadastroController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        return "cadastro";
    }
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestParam String nome, @RequestParam String email, @RequestParam String senha, Model model) {
        Optional<Usuario> user = this.userRepository.findByEmail(email);

        if (user.isEmpty()) {
            Usuario usuario = new Usuario(new DadosCadastroUsuario(nome, email, senha));
            var senhaCriptografada = passwordEncoder.encode(senha);
            usuario.setSenha(senhaCriptografada);
            usuario.setNome(nome);
            usuario.setEmail(email);
            userRepository.save(usuario);
            if (usuario.getRole().equals(Funcao.ADMIN.name())) {
                String token = this.tokenService.generateToken(usuario);
                return ResponseEntity.ok(new ValidacaoDadosUsuario(usuario.getNome(), token));
            }
            return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario.getNome(), usuario.getEmail(), usuario.getRole()));
        }
        return ResponseEntity.badRequest().build();
    }
}
