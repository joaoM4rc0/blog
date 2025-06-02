package blog.com.dominio;


import blog.com.Dto.DadosCadastroPost;
import blog.com.Dto.DadosEditarPost;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
@Entity
@Table(name = "post")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String title;
    @NotNull
    private String conteudo;
    @Column(name = "author_id")
    private long authorId;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "is_Destaque")
    private boolean isDestaque;
    public Posts(DadosCadastroPost post) {
        this.title = post.title();
        this.conteudo = post.conteudo();
        this.authorId = post.author_id();
        this.createdAt = LocalDate.now();
        isDestaque = true;
    }

    public Posts() {
    }
    public void editaPost(DadosEditarPost dadosEditados) {
        if (dadosEditados.title() != null && !dadosEditados.title().isEmpty()) {
            this.title = dadosEditados.title();
        }
        if (dadosEditados.conteudo() != null && !dadosEditados.conteudo().isEmpty()) {
            this.conteudo= dadosEditados.conteudo();
        }
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
