package blog.com.controller;

import blog.com.Dto.DadosCadastroComentario;
import blog.com.dominio.Comentario;
import blog.com.repository.ComentarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/post/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadastroComentario> criarComentario(@RequestBody @Valid DadosCadastroComentario comentario, UriComponentsBuilder uriBuilder) {
        Comentario comentarios = new Comentario(comentario);
        comentarioRepository.save(comentarios);
        URI uri = uriBuilder.path("comentario/{id}").buildAndExpand(comentarios.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCadastroComentario(comentarios.getUsuario_id(), comentarios.getPost_id(),  comentarios.getTexto()));
    }

    @DeleteMapping
    @Transactional
    public void deletarComentario(@PathVariable long id) {
        comentarioRepository.getReferenceById(id);
        comentarioRepository.deleteById(id);
    }

}
