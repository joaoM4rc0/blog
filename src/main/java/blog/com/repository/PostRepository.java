package blog.com.repository;

import blog.com.dominio.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findByIsDestaqueTrue();
    List<Posts> findTop10ByOrderByCreatedAtDesc();
}
