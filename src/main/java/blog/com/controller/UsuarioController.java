package blog.com.controller;

import blog.com.Dto.DadosCadastroUsuario;
import blog.com.dominio.Usuario;
import blog.com.repository.UserRepository;
import blog.com.Dto.DadosSalvarUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PutMapping
    @Transactional
    public ResponseEntity<DadosCadastroUsuario> atualizaSenha(@RequestBody @Valid DadosSalvarUsuario dados) {
        Usuario usuario = getReferenceById(dados.id());
        usuario.atualizaSenha(dados.senha());
        return ResponseEntity.ok(dadosCadastroUsuario(usuario));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deletarConta(@PathVariable long id) {
        getReferenceById(id);
        userRepository.deleteById(id);
    }
    private Usuario getReferenceById(Long id) {
        return userRepository.getReferenceById(id);
    }

    private DadosCadastroUsuario dadosCadastroUsuario(Usuario usuario) {
        return new DadosCadastroUsuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}