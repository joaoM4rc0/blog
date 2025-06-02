package blog.com.service;

import blog.com.dominio.Posts;
import blog.com.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Posts buscarPostEmDestaque() {
        Optional<Posts> postDestaque = postRepository.findByIsDestaqueTrue();
        System.out.println("Post em destaque encontrado: " + postDestaque.isPresent()); // Debug
        return postDestaque.orElseThrow(() -> new RuntimeException("Nenhum post em destaque encontrado."));
    }
    public List<Posts> listarPostsRecentes() {
        return postRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
