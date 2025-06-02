package blog.com.controller;

import blog.com.Dto.DadosEditarPost;
import blog.com.dominio.Comentario;
import blog.com.dominio.Posts;
import blog.com.dominio.Usuario;
import blog.com.repository.ComentarioRepository;
import blog.com.repository.PostRepository;
import blog.com.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/acao")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Operation(summary = "Listar todos os usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "usuarios listados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping("/Listarusuarios")
    @Transactional
    public List<UsuariosListados> listar() {
        return userRepository.findAll().stream().map(UsuariosListados::new).toList();
    }
    @Operation(summary = "Listar todos os comentarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "comentarios listados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping("/ListarComentarios")
    @Transactional
    public List<ComentarioUsuario> comentarios() {
        return comentarioRepository.findAll().stream().map(ComentarioUsuario::new).toList();
    }
    private record ComentarioUsuario(long id_author, String texto) {
        private ComentarioUsuario(Comentario comentario) {
            this(comentario.getUsuario_id(), comentario.getTexto());
        }
    }
    @Operation(summary = "Listar todos os posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "posts listados com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping("/ListarPost")
    @Transactional
    public List<postUsuario> Post() {
        return postRepository.findAll().stream().map(postUsuario::new).toList();
    }
    @Operation(summary = "deleta post pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "post deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPost(@Parameter(description = "ID do post a ser deletado", required = true)@PathVariable long id) {
        postRepository.getReferenceById(id);
        postRepository.deleteById(id);
    }
    private @NotNull Posts getReferenceById(DadosEditarPost dadosEditados) {
        return postRepository.getReferenceById(dadosEditados.id());
    }
    private record postUsuario(String tilte, String conteudo) {
        private postUsuario(Posts posts) {
            this(posts.getTitle(), posts.getConteudo());
        }
    }
    public record UsuariosListados(String nome, String email) {
        private UsuariosListados(Usuario usuario) {
            this(usuario.getNome(), usuario.getEmail());
        }
    }
}
