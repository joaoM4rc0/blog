package blog.com.Dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroComentario(@NotNull long id_author,@NotNull long post_id,  String texto) {
}
