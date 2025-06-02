package blog.com.Dto;

import jakarta.validation.constraints.NotNull;

public record DadosEditarPost(@NotNull long id, String title, String conteudo) {
}
