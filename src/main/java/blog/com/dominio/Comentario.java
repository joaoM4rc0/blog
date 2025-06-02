package blog.com.dominio;

import blog.com.Dto.DadosCadastroComentario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String texto;
    private boolean is_approved;
    @NotNull
    private long usuario_id;
    @NotNull
    private long post_id;
    {
        this.is_approved = true;
    }
    public Comentario(DadosCadastroComentario comentario) {
        this.usuario_id = comentario.id_author();
        this.post_id = comentario.post_id();
        this.texto = comentario.texto();
    }

    public Comentario() {
    }
    public long getPost_id() {return post_id;}
    public long getUsuario_id() {return usuario_id;}
    public long getId() {
        return id;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
