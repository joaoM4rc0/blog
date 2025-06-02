package blog.com.controller;

import blog.com.Dto.DadosCadastroPost;
import blog.com.Dto.DadosEditarPost;
import blog.com.dominio.Posts;
import blog.com.repository.PostRepository;
import blog.com.Dto.DadosDetalhamentoPost;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @PostMapping
    @Transactional

    public ResponseEntity<DadosCadastroPost> criarPost(@RequestBody @Valid DadosCadastroPost post, UriComponentsBuilder uriBuilder) {
        Posts posts = new Posts(post);
        postRepository.save(posts);
        URI uri = uriBuilder.path("/post/{id}").buildAndExpand(posts.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCadastroPost(posts.getTitle(), posts.getConteudo(),posts.getAuthorId()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> editarPost(@RequestBody @Valid DadosEditarPost dadosEditados) {
        Posts posts = getReferenceById(dadosEditados);
        posts.editaPost(dadosEditados);
        return ResponseEntity.ok(new DadosDetalhamentoPost(dadosEditados.title(), dadosEditados.conteudo()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPost(@PathVariable long id) {
        postRepository.getReferenceById(id);
        postRepository.deleteById(id);
    }

    private @NotNull Posts getReferenceById(DadosEditarPost dadosEditados) {
        return postRepository.getReferenceById(dadosEditados.id());
    }
}
